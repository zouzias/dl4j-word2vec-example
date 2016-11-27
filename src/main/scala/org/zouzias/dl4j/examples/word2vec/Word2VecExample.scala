package org.zouzias.dl4j.examples.word2vec

import org.datavec.api.util.ClassPathResource
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer
import org.deeplearning4j.models.word2vec.Word2Vec
import org.deeplearning4j.text.sentenceiterator.{BasicLineIterator, SentenceIterator}
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor
import org.deeplearning4j.text.tokenization.tokenizerfactory.{DefaultTokenizerFactory, TokenizerFactory}
import org.slf4j.{Logger, LoggerFactory}


object Word2VecExample {

  private val log: Logger = LoggerFactory.getLogger("Word2VecExample")

  def main(args: Array[String]) {

    if (args.length != 2){
      System.err.println("Usage: sbt \"run <filename of strings> query\"")
      System.err.println("Example: sbt \"run raw_sentences.txt day\"")

      return
    }

    // Gets Path to Text file
    val filePath = args(0)
    val query = args(1)

    log.info("Load & Vectorize Sentences....")
    // Strip white space before and after for each line
    val iter: SentenceIterator = new BasicLineIterator(filePath)
    // Split on white spaces in the line to get words
    val t : TokenizerFactory= new DefaultTokenizerFactory()

    /*
        CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
        So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
        Additionally it forces lower case for all tokens.
     */
    t.setTokenPreProcessor(new CommonPreprocessor())

    log.info("Building model....")
    val vec: Word2Vec = new Word2Vec.Builder()
      .minWordFrequency(5)
      .iterations(1)
      .layerSize(100)
      .seed(42)
      .windowSize(5)
      .iterate(iter)
      .tokenizerFactory(t)
      .build()

    log.info("Fitting Word2Vec model....")
    vec.fit()

    log.info("Writing word vectors to text file....")

    // Write word vectors to file
    WordVectorSerializer.writeWordVectors(vec, "pathToWriteto.txt")

    // Prints out the closest 10 words to "day". An example on what to do with these Word Vectors.
    log.info("Closest Words:")
    val lst = vec.wordsNearest(query, 10)
    println(s"10 Words closest to '${query}': " + lst)
  }

}

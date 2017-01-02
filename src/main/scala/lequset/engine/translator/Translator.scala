package lequset.engine.translator

object Translator {
  def apply(request: LeRequest) = {
    TranslateToSomeNativeDBSpeak(request)
  }
}
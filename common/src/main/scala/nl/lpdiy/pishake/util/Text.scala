package nl.lpdiy.pishake.util

object Text {

  def toSnakeCase(s: String, dash: Boolean = true): String = {
    var lower = false
    val snake = new StringBuilder
    
    for(c <- s.toCharArray) {
      val previous = lower
      lower = Character.isLowerCase(c)
      if(previous && !lower) 
        if(dash) snake.append("-") else snake.append("_")
      snake.append(c)
    }
    
    snake.toString().toLowerCase
  }
}

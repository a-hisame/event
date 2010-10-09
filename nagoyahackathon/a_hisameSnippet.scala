package nagoyascala.snippet;

import scala.xml.{Node, NodeSeq, Text}
import scala.xml.NodeSeq._

import net.liftweb.http.S._
import net.liftweb.http.SHtml._
import net.liftweb.http.RequestVar
import net.liftweb.http.SessionVar
import net.liftweb.util.Helpers._
import net.liftweb.common._

class a_hisameSnippet {
  def hello(xhtml : NodeSeq) : NodeSeq = {
    <span>hello, a_hisame's world!</span>  
  }

  def now(xhtml: NodeSeq) = {
   import java.text.DateFormat
   import java.util.{Date, Locale}
   <span>{DateFormat.getDateInstance(
     DateFormat.LONG,
     Locale.JAPANESE) format new Date}</span>
  }
}

class AHisameHello {

  object ms extends SessionVar(Full(List[String]()))

  def show(xhtml: NodeSeq): NodeSeq = {
    bind("hello", xhtml, 
      "who_field" --> text("",
                      v => {
		        val vs = v :: ms.openOr(Nil)
		        ms(Full(vs))
		      }) 
                      % ("size" -> "20") 
		      % ("id" -> "who_field"), 
      "submit" --> submit(?("Send"), () => {println(ms.openOr(Nil))} ), 
      "clear" --> submit(?("Clear"), () => {ms(Full(List[String]()))} ),
      "history" --> history(ms.openOr(Nil)) 
    )
  }

  private def history(xs: List[String]) = {
    <ul>{
      xs.map{ x => <li>{ x }</li> } 
    }</ul>
  }
}

class AHisameSchemee {

  import nagoyahackathon.Schemee
  import nagoyahackathon.nya.Neko

  object in extends RequestVar(Full(""))
  object out extends RequestVar(Full("")) 

  def show(xhtml: NodeSeq): NodeSeq = {
    bind("hello", xhtml, 
      "program_input" --> 
         textarea(in.openOr(""), v => { 
	   in(Full(v))
	   out(Full(Neko.solve(v)))
	 }), 
      "submit" --> submit(?("Run"), () => {} ), 
      "program_output" -->
         textarea(out.openOr(""), v => {} ) 
    )
  } 
}

import akka.actor.{ActorLogging, Props, ActorSystem, Actor}
import akka.routing.FromConfig
import scala.concurrent.Await
import scala.concurrent.duration._

sealed trait Command
object Initialized extends Command
case class Work(part:Int) extends Command
case class Completed(part:Int, duration: Long) extends Command

class SupervisorActor extends Actor with ActorLogging {

  val router = context.actorOf(FromConfig.props(Props[WorkerActor]), "router")

  def receive = {
    case Initialized => for(i <- 1 to 50) router ! Work(i)
    case Completed(part, duration) => log.debug(s"completing work $part took $duration milliseconds")
  }

}

class WorkerActor extends Actor with ActorLogging {

  final val rnd = new scala.util.Random()

  def receive: Receive = {
    case Work(part) =>
      log.debug(s"working on part $part")
      val duration: Long = rnd.nextInt(1000)
      Thread.sleep(duration)
      sender ! Completed(part, duration)
  }

}

object Program {

  def main(args: Array[String]) {

    val system = ActorSystem("system")
    val actor = system.actorOf(Props[SupervisorActor], "supervisor")

    actor ! Initialized

    //system.terminate()

  }

}

import akka.actor.{ActorLogging, Props, ActorSystem, Actor}
import scala.concurrent.Await
import scala.concurrent.duration._

sealed trait Command
object Initialized extends Command

class SimpleActor extends Actor with ActorLogging {
  def receive = {
    case Initialized => log.debug("initialized")
  }
}

object Program {

  def main(args: Array[String]) {

    val system = ActorSystem("system")
    val actor = system.actorOf(Props[SimpleActor], "simple1")

    actor ! Initialized

    system.terminate()

    Await.result(system.whenTerminated, 2 seconds)

  }

}

package nl.lpdiy.pishake.util

import akka.actor._


trait ActorReference {

  def name: String = Text.toSnakeCase(getClass.getSimpleName)
}

trait ActorDescription {
  def props(args: Any*): Props

  def name: String = Text.toSnakeCase(getClass.getSimpleName)
}

object ActorSupport {
  def actorOf(actorDescription: ActorDescription, args: Any*)(implicit mailbox: String = "akka.actor.default-mailbox", actorSystem: ActorSystem) =
    actorSystem.actorOf(actorDescription.props(args: _*).withMailbox(mailbox), actorDescription.name)
}

trait ActorSupport {
  this: Actor =>

  def actorOf(actorDescription: ActorDescription, args: Any*)(implicit mailbox: String = "akka.actor.default-mailbox") =
    ActorSupport.actorOf(actorDescription)(mailbox, context.system)

  def actorFor(actorDescription: ActorDescription) =
    context.actorSelection(s"../${actorDescription.name}")

  def actorForPath(path: String, actor: String): ActorSelection =
    context.actorSelection(s"$path/user/$actor")
}
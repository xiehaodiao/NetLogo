// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim.etc

import org.nlogo.api.LogoException
import org.nlogo.nvm.{AssemblerAssistant, Command, Context, CustomAssembled}

final class _ifelse extends Command with CustomAssembled {
  override def toString: String = super.toString + ":+" + offset

  @throws[LogoException]
  override def perform(context: Context): Unit = perform_1(context, argEvalBooleanValue(context, 0))

  def perform_1(context: Context, arg0: Boolean): Unit = context.ip = if (arg0) next
  else offset

  override def assemble(a: AssemblerAssistant): Unit = {
    a.add(this)
    a.block(1)
    a.goTo()
    a.resume()
    a.block(2)
    a.comeFrom()
  }
}
// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim.etc

import org.nlogo.api.LogoException
import org.nlogo.core.Pure
import org.nlogo.nvm.{Context, Reporter, RuntimePrimitiveException}

final class _ifelsevalue extends Reporter with Pure {
  @throws[LogoException]
  override def report(context: Context): AnyRef = {
    var i = 0
    while (i < args.length - 1) {
      if (argEvalBooleanValue(context, i)) {
        return argEvalAnonymousReporter(context, i + 1).report(context, Array.empty[AnyRef])
      }
      i += 2
    }
    if (i < args.length)
      argEvalAnonymousReporter(context, args.length - 1).report(context, Array.empty[AnyRef])
    else
      throw new RuntimePrimitiveException(context, this, "TODO")
  }
}

// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.swing

import javax.swing.JFrame

object FullScreenUtilities {
  // See http://mail.openjdk.java.net/pipermail/awt-dev/2017-March/012642.html
  // for notes on why this changed when we adopted Java 9. RG 2/14/18
  def setWindowCanFullScreen(frame: JFrame, can: Boolean) {
    try {
      frame.getRootPane.putClientProperty("apple.awt.fullscreenable", can)
    } catch { // ignore
      case e: Exception =>
    }
  }
}

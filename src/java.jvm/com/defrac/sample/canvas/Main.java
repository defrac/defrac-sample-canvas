package com.defrac.sample.canvas;

import defrac.ui.FrameBuilder;
import defrac.util.Color;

/**
 *
 */
public final class Main {
  public static void main(String[] args) {
    FrameBuilder.
        forScreen(new ScribblerScreen()).
        backgroundColor(Color.Web.WHITE).
        show();
  }
}

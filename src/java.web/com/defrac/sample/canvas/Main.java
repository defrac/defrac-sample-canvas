package com.defrac.sample.canvas;

import defrac.ui.FrameBuilder;

/**
 *
 */
public final class Main {
  private static final String CUSTOM_CSS = ""+
      "body, html { background: #eee; padding: 0; margin: 0; width: 100%; height: 100% }\n" +
      "#screen { background: #fff; position: absolute; top: 50%; left: 50%; width: 640px; height: 360px; margin-left: -320px; margin-top: -180px; box-shadow: 0px 0px 32px #333 }\n" +
      "#screen > canvas { margin: 0; padding: 0; margin: 0; display: block; width: 100%; height: 100% }\n" +
      "canvas:focus { outline: none }\n" +
      "p { text-align: center; font: 16pt sans-serif; }";

  public static void main(String[] args) {
    FrameBuilder.
        forScreen(new ScribblerScreen()).
        containerById("screen").
        css(CUSTOM_CSS).
        show();
  }
}

package com.defrac.sample.canvas;

import defrac.app.Bootstrap;
import defrac.app.GenericApp;
import defrac.display.Canvas;
import defrac.display.DisplayObject;
import defrac.display.event.UIActionEvent;
import defrac.display.graphics.Graphics;
import defrac.util.Color;

/**
 *
 */
public final class CanvasApp extends GenericApp {
  public static void main(String[] args) {
    Bootstrap.run(new CanvasApp());
  }

  DisplayObject.Listener painter = new DisplayObject.SimpleListener() {
    @Override
    public void onPointerDown(DisplayObject target, UIActionEvent event) {
      brush.onPaintStart(graphics, event.pos);
    }

    @Override
    public void onPointerMove(DisplayObject target, UIActionEvent event) {
      if(event.isActive) {
        brush.onPaintUpdate(graphics, event.pos);
      }
    }

    @Override
    public void onPointerUp(DisplayObject target, UIActionEvent event) {
      brush.onPaintStop(graphics, event.pos);
    }
  };

  Brush brush = new ScribblerBrush();
  Canvas canvas;
  Graphics graphics;

  @Override
  protected void onStart() {
    backgroundColor(Color.WHITE);
    createCanvas(width(), height());
  }

  @Override
  protected void onResize(float width, float height) {
    brush.reset();
    destroyCanvas();
    createCanvas(width, height);
  }

  private void destroyCanvas() {
    if(canvas != null) {
      removeChild(canvas);
      canvas.listener(null);
    }
  }

  private void createCanvas(float width, float height) {
    canvas = addChild(new Canvas(width, height));
    canvas.listener(painter);
    graphics = canvas.graphics();
  }
}

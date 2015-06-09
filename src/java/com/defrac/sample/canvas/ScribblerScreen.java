package com.defrac.sample.canvas;

import defrac.display.Canvas;
import defrac.display.DisplayObject;
import defrac.display.Stage;
import defrac.display.event.UIActionEvent;
import defrac.display.event.raw.ResizeEvent;
import defrac.display.graphics.Graphics;
import defrac.event.EventListener;
import defrac.lang.Procedure;
import defrac.ui.DisplayList;
import defrac.ui.Screen;
import defrac.util.Color;

/**
 *
 */
public final class ScribblerScreen extends Screen {
  DisplayList displayList;
  Brush brush;
  DisplayObject.Listener painter;
  Canvas canvas;
  Graphics graphics;

  @Override
  protected void onCreate() {
    super.onCreate();

    displayList = new DisplayList();
    brush = new ScribblerBrush();
    painter = new DisplayObject.SimpleListener() {
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

    displayList.onStageReady(new Procedure<Stage>() {
      @Override
      public void apply(final Stage stage) {
        stage.backgroundColor(Color.Web.WHITE);
        createCanvas(stage);

        stage.globalEvents().onResize.add(new EventListener<ResizeEvent>() {
          @Override
          public void onEvent(ResizeEvent event) {
            brush.reset();
            destroyCanvas(stage);
            createCanvas(stage);
          }
        });
      }
    });

    rootView(displayList);
  }

  @Override
  protected void onPause() {
    super.onPause();
    displayList.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    displayList.onResume();
  }

  void destroyCanvas(Stage stage) {
    if(canvas != null) {
      stage.removeChild(canvas);
      canvas.listener(null);
    }
  }

  void createCanvas(Stage stage) {
    canvas = new Canvas(stage.width(), stage.height());
    canvas.listener(painter);
    graphics = canvas.graphics();
    stage.addChild(canvas);
  }
}

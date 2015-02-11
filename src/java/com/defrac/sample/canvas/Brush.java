package com.defrac.sample.canvas;

import defrac.display.graphics.Graphics;
import defrac.geom.Point;

/**
 *
 */
public interface Brush {
  void onPaintStart(Graphics graphics, Point pos);
  void onPaintUpdate(Graphics graphics, Point pos);
  void onPaintStop(Graphics graphics, Point pos);
  void reset();
}

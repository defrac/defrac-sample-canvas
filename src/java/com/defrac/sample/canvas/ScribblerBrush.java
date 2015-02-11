package com.defrac.sample.canvas;

import defrac.display.graphics.Graphics;
import defrac.geom.Point;

class ScribblerBrush implements Brush {
  // scribbler originally invented by zefrank http://www.zefrank.com/scribbler/
  // harmony fork by mrdoob http://mrdoob.com/projects/harmony/

  float[] points = new float[8192];
  int numPoints = 0;

  @Override
  public void onPaintStart(Graphics graphics, Point pos) {}

  @Override
  public void onPaintUpdate(Graphics graphics, Point pos) {
    float cx = pos.x;
    float cy = pos.y;

    if(numPoints == points.length) {
      float[] oldPoints = points;
      float[] newPoints = new float[oldPoints.length << 1];
      System.arraycopy(oldPoints, 0, newPoints, 0, numPoints);
      points = newPoints;
    }

    points[numPoints    ] = cx;
    points[numPoints + 1] = cy;
    numPoints += 2;

    for(int i = 0; i < numPoints;) {
      float lx = points[i++];
      float ly = points[i++];
      float dx = lx - cx;
      float dy = ly - cy;
      float d = dx * dx + dy * dy;
      if(d < 1000.0f) {
        graphics.
            strokeStyle(0.125f, 0.125f, 0.125f, (1.0f - (d / 1000.0f)) * 0.1f).
            beginPath().
            moveTo(cx, cy).
            lineTo(lx, ly).
            stroke();
      }
    }
  }

  @Override
  public void onPaintStop(Graphics graphics, Point pos) {}

  @Override
  public void reset() {
    points = new float[8192];
  }
}

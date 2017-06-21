package objects;

import java.awt.*;

/**
 * Created by Ruslan on 21.06.2017.
 */
public class Desk {

    private int size;
    private double cellSize;
    private Cell[][] cellMatrix;

    private GraphPanel graphPanel; //need or not?

    private void fillMatrix() {
        int x = 0;
        int y = 0;
        int cellColor = 0;

        for (int i = 0; i < size; i++) {
            x = 0;
            for (int j = 0; j < size; j++) {
                cellMatrix[i][j] = new Cell(x, y, cellSize, cellColor);
                x += cellSize;
                cellColor++;
            }
            if (size % 2 == 0) cellColor++;
            y += cellSize;
        }
    }

    public Desk(int size, GraphPanel graphPanel) {
        this.size = size;
        this.graphPanel = graphPanel;
        this.cellSize = graphPanel.getWidth() / size;
        cellMatrix = new Cell[size][size];
        fillMatrix();
    }

    public void paintDesk(Graphics2D graphics2D) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graphics2D.setColor(cellMatrix[i][j].getColor());
                graphics2D.fill(cellMatrix[i][j]);
                graphics2D.draw(cellMatrix[i][j]);
            }
        }

    }
}

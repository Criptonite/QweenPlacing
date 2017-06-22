package objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniil on 21.06.2017.
 * Extended by Dmitriy on 21.06.2017.
 *
 *
 * Class will redraw itself when it'is necessary.
 *
 */
public class Desk {

    //Image owner needs to be implemented in separate class
    private BufferedImage qweenImage;
    private int size;
    private double cellSize;
    private Cell[][] cellMatrix;

    private GraphPanel graphPanel; //NEED

    /**
     * Method initializing cell matrix
     */
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
        try {
            qweenImage = resize(ImageIO.read(new File("C:\\QweenPlacing\\resources\\images\\qween.png")),
                    (int) cellSize,
                    (int) cellSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method painting desk
     * @param graphics2D graphics provider
     */
    public void paintDesk(Graphics2D graphics2D) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                drawElement(graphics2D, i, j);
            }
        }
        if (qweenImage != null) graphics2D.drawImage(qweenImage, 0, 0, graphPanel);
    }


    /**
     * @param img  image to resize
     * @param newW needed width
     * @param newH heeded height
     * @return scaled image
     */
    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        BufferedImage image = new BufferedImage(newH, newH, img.getType());
        int w = img.getWidth();
        int h = img.getHeight();
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();

        return image;
    }


    /**
     * Method used for drawing cells in one string
     * @param graphics2D graphic provider
     * @param i index of raw
     * @param j index of coll
     */
    private void drawElement(Graphics2D graphics2D, int i, int j) {
        graphics2D.setColor(cellMatrix[i][j].getColor());
        graphics2D.fill(cellMatrix[i][j]);
        graphics2D.draw(cellMatrix[i][j]);
    }

    /**
     * Method to remove cell from desk
     * @param graphics2D graphic provider
     * @param i index of raw
     * @param j index of coll
     */
    private void removeElement(Graphics2D graphics2D, int i, int j) {
        Cell cell = cellMatrix[i][j];
        graphics2D.clearRect((int) cell.getX(), (int) cell.getY(), (int) cellSize, (int) cellSize);
    }


    /**
     * Method to remove cell from desk
     * @param graphics2D graphic provider
     * @param color new cell color
     * @param i index of raw
     * @param j index of coll
     */
    private void changeCellCollor(Graphics2D graphics2D, Color color, int i, int j) {
        Cell cell = cellMatrix[i][j];
        cell.setColor(color);
        removeElement(graphics2D, i, j);
        drawElement(graphics2D, i, j);
    }


    //Here will be implemented backtracking later........
    public void searchSolutions(Graphics2D graphics2D) {

    }
}

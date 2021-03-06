package moa.tasks.JSON;

import java.util.ArrayList;

/**
 * Manage the list of all cells
 * Add new cells
 * Create a Jupyter Notebook as IPYNB file
 *
 * @author Truong To (todinhtruong at gmail dot com)
 */
public class Notebook {
    private ArrayList<NotebookCell> notebookCells;
    private NotebookCell cell;
    private StringBuilder notebook;

    public Notebook(){
        notebookCells = new ArrayList<NotebookCell>();
        notebook = new StringBuilder();
    }

    public StringBuilder createNotebook() {
        int count = 1;
        for (int i = 0; i < notebookCells.size(); i++) {
            if (notebookCells.get(i) instanceof CodeCell){
                notebookCells.get(i).setWorkingMode(NotebookCell.Mode.EXECUTION_COUNT);
                notebookCells.get(i).add(Integer.toString(count));
                count ++;
            }
            notebookCells.get(i).createCell();
            notebook.append("\n")
                    .append(notebookCells.get(i).getCell())
                    .append(",\n");
        }
        notebook.deleteCharAt(notebook.length()-2)
                .insert(0, "{\n" + "\"" + "cells" + "\": [")
                .append("],\n")
                .append("\"metadata\": {},\n\"nbformat\": 4,\n\"nbformat_minor\": 0\n}\n");
        return notebook;
    }

    public NotebookCell addCode(){
        cell = new CodeCell();
        notebookCells.add(cell);

        return cell;
    }

    public NotebookCell addMarkdown(){
        cell = new MarkDownCell();
        notebookCells.add(cell);

        return cell;
    }

    public NotebookCell addRaw(){
        cell = new RawCell();
        notebookCells.add(cell);

        return cell;
    }

    public StringBuilder getNotebook() {
        return notebook;
    }

    /**public NotebookCell getLastCell(){
        return notebookCells.get(notebookCells.size()-1);
    }**/

    public ArrayList<NotebookCell> getNotebookCells() {
        return notebookCells;
    }

    public NotebookCell getCellByIndex(int index){
        return notebookCells.get(index);
    }
}

package uta.cse3310;

public class WordLocation
{
   private int startRow;
   private int startColumn;
   private int endRow;
   private int endColumn;

   public WordLocation(int startRow, int startColumn, int endRow, int endColumn)
   {
      this.startRow = startRow;
      this.startColumn = startColumn;
      this.endRow = endRow;
      this.endColumn = endColumn;
   }
   
   // find equality of objects (coordinates)
   public boolean equals(int startRow, int startColumn, int endRow, int endColumn)
   {
      return this.startRow == startRow && this.startColumn == startColumn &&
      this.endRow == endRow && this.endColumn == endColumn;
   }
   
   // toString for debugging
   @Override
   public String toString()
   {
      return "Start: (" + startRow + ", " + startColumn + ") End: (" + endRow + ", " + endColumn + ")";
   }
}
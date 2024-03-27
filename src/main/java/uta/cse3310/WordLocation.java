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
   @Override
   public boolean equals(Object o)
   {
      if(this == o)
      {
         return true;
      }
       
      if(o == null || getClass() != o.getClass())
      {
         return false;
      }
      
      WordLocation that = (WordLocation) o;
      return startRow == that.startRow && startColumn == that.startColumn && endRow == that.endRow && endColumn == that.endColumn;
   }
   
   // toString for debugging
   @Override
   public String toString()
   {
      return "Start: (" + startRow + ", " + startColumn + ") End: (" + endRow + ", " + endColumn + ")";
   }
}
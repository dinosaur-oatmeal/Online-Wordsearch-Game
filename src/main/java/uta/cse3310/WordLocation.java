package uta.cse3310;

public class WordLocation
{
   public int lastLocation;
   public int location;

   public WordLocation(int lastLocation, int location)
   {
      this.lastLocation = lastLocation;
      this.location = location;
   }
   
   // find equality of objects (coordinates)
   public boolean equals(WordLocation location)
   {
      return this.lastLocation == location.lastLocation && this.location == location.location;
   }
   
   // toString for debugging
   @Override
   public String toString()
   {
      return "Start: ("+ lastLocation + ") End: (" + location + ")";
   }

   public int getLastLocation()
   {
      return lastLocation;
   }

   public int getLocation()
   {
      return location;
   }
}
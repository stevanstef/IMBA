import java.io.*;
class Search
{
       public String searching(String fileName, String data [][], int item, String input)
       {for (int i = 0; i < data.length; i++)
       {
              if (data [i][item].equals(input))
              {
                     return input;
              }
       }
       return null;
}}
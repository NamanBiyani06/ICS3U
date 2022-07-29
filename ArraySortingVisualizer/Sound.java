import java.io.*;
import javax.sound.sampled.*;


class Sound
  {
    public static void beep()
    {
      //System.out.println("beep");
      try 
      {
        File beep = new File("beep.wav");
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
    
        stream = AudioSystem.getAudioInputStream(beep);
        format = stream.getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
        System.out.println("beep");
        Thread.sleep(450);
        clip.close();
     }
     catch (Exception e) 
     {
        //System.out.println("L");
     }
  }
} 

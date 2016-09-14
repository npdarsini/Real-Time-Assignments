/**
 * Created by meets on 9/12/2016.
 */

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.typography.hershey.HersheyFont;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class FindingClarifaiTags {
    private static String APP_ID = "ol13P7yPHGuf6H8d4l4ihNA2d491dA393MOyd36N";
    private static String APP_SECRET = "B_nOVCf1hA_UbcvjMra4FFMeWz2amaAu4IdI4gXS";

    public static void main(String args[]) throws IOException, InterruptedException {
        FindingClarifaiTags t = new FindingClarifaiTags();
        t.getTags();
    }

    public static void getTags() throws IOException, InterruptedException {
        ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
        List<RecognitionResult> results = null;
        //int i=0;

        File path = new File("output2/mainframes");

        File[] files = path.listFiles();



        for (int i = 0; i < files.length; i++) {
            results = clarifai.recognize(new RecognitionRequest(files[i]));

            for (Tag tag : results.get(0).getTags()) {
                System.out.println(tag.getName() + ": " + tag.getProbability());

                if (tag.getProbability()> 0.87 && tag.getProbability()< 0.88) {
                    MBFImage image = ImageUtilities.readMBF(files[i]);

                    image.drawText(tag.getName(), 90, 150, HersheyFont.ASTROLOGY, 20, RGBColour.BLACK);

                    DisplayUtilities.displayName(image, "videoFrames");


                }

            }
        }

/*
            results = clarifai.recognize(new RecognitionRequest(files));
        for (int i = 0; i < files.length; i++) {
            for (Tag tag : results.get(i).getTags()) {
                System.out.println(tag.getName() + ": " + tag.getProbability());
                /*MBFImage image = ImageUtilities.readMBF(files[i]);

                image.drawText(tag.getName(), 90, 150, HersheyFont.ASTROLOGY, 20, RGBColour.BLACK);

                DisplayUtilities.displayName(image, "videoFrames");*/
                //List l1 = tag.getName();
/*
                String name = "output/Tags/" + i + ".jpg";
                File outputFile = new File(name);
                try {
                    ImageIO.write(, "jpg", outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
*/
    }
}


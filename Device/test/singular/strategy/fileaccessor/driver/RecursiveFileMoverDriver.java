package singular.strategy.fileaccessor.driver;

import singular.strategy.fileaccessor.RecursiveFileAccessor;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Created by Andrew Michel on 1/20/2019.
 */
public class RecursiveFileMoverDriver
{
    static final int TOTAL_ARGUMENTS = 3, DIRECTORY_PARAMETERS = 2, ORIGINAL_DIRECTORY_INDEX = 0,
                        TARGET_DIRECTORY_INDEX = 1, CONTAINS_INDEX = 2;


    public static void main(String[] args)
    {
        if(args.length < 1) // no commandline arguments, use Scanner
        {
            Scanner scan = new Scanner(System.in);
            args = new String[3];

            System.out.print("Enter original directory: ");
            args[0] = scan.nextLine();

            System.out.print("Enter target directory: ");
            args[1] = scan.nextLine();

            System.out.print("Enter extension type: ");
            args[2] = scan.nextLine();
        }

        // parse args to files
        ArrayList<File> files = new ArrayList<>();

        String search = "";

        if(args.length >= 2)
        {
            search = args[2].toLowerCase();
        }

        if(!parseForArguments(args, files))
        {
            System.out.println("Invalid arguments. terminating program.");
            System.exit(1);
        }

        RecursiveFileAccessor rfa = new RecursiveFileAccessor(files.get(ORIGINAL_DIRECTORY_INDEX));

        System.out.println("ACCESS: " + rfa.bottomUpAccess());
        System.out.println("ALPHA: " + rfa.getAlpha());

        Iterator<File> iterator = rfa.getLinkedHashSet().iterator();

        File current;

        System.out.println("ITERATOR HAS NEXT: " + iterator.hasNext());

        int moved = 0, fails = 0, matched = 0;
        String substring, updatedPath;
        boolean successfulMove;

        File parent, newPath;

        while(iterator.hasNext())
        {
            current = iterator.next();

            if(current.getName().toLowerCase().contains(search))
            {
                //System.out.println(current.getName());

                ++matched;

                substring = current.getAbsolutePath().substring(files.get(ORIGINAL_DIRECTORY_INDEX).getAbsolutePath().length());

                updatedPath = files.get(TARGET_DIRECTORY_INDEX).getAbsolutePath() + substring;

                newPath = new File(updatedPath);

                parent = newPath.getParentFile();

                if(!parent.exists())
                {
                    recursivelyMakeDirectory(parent);
                    System.out.println("MAKING DIRECTORY: " + parent.getAbsolutePath() + " - SUCCESS: " + parent.exists());
                }
                else
                {
                    System.out.println("DIRECTORY: " + parent.getAbsolutePath() + " - ALREADY EXISTS. PROCEEDING");
                }

                successfulMove = current.renameTo(newPath);

                if(successfulMove)
                {
                    ++moved;
                }
                else
                {
                    ++fails;
                }

                System.out.println(updatedPath + " - SUCCESSFUL: " + successfulMove);
            }
        }

        System.out.println("TOTAL FILES: " + rfa.getLinkedHashSet().size());
        System.out.println("FILES MATCHED: " + matched);
        System.out.println("FILES MOVED: " + moved);
        System.out.println("FILES FAILED: " + fails);
    }

    private static boolean parseForArguments(String[] args, ArrayList<File> reference)
    {
        if(args.length < DIRECTORY_PARAMETERS)
        {
            return false;
        }

        File current;

        for(int i = 0; i < args.length && i < DIRECTORY_PARAMETERS; ++i)
        {
            current = new File(args[i]);
            System.out.println("ARGS[" + i + "]: " + args[i] + " dir: " + current.isDirectory() + " file: " + current.isFile() + " exists: " + current.exists() + " FILE: " + current);
            if(!current.isDirectory())
            {
                System.out.println(current.getPath() + " is not a valid directory! terminating program");
                System.exit(2);
            }
            else
            {
                reference.add(current);
            }
        }

        return true;
    }

    private static void recursivelyMakeDirectory(File currentFile)
    {
        if(!currentFile.exists())
        {
            recursivelyMakeDirectory(currentFile.getParentFile());

            currentFile.mkdir();
        }
    }
}

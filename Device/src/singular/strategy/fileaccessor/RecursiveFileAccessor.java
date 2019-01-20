package singular.strategy.fileaccessor;

import java.io.File;
import java.util.*;

/**
 * Created by Andrew Michel on 1/20/2019.
 */
public class RecursiveFileAccessor
{
    // Instance variables
    // Prevents duplicates while maintaining insertion order
    private LinkedHashSet<File> linkedHashSet;
    private File alpha;

    // Constructors
    public RecursiveFileAccessor()
    {
        init(null, 10);
    }

    public RecursiveFileAccessor(File topFile)
    {
        init(topFile, 10);
    }

    public RecursiveFileAccessor(File topFile, int initialCapacity)
    {
        init(topFile, initialCapacity);
    }

    // Methods

    private void init(File topFile, int initialCapacity)
    {
        linkedHashSet = new LinkedHashSet<File>(initialCapacity);
        alpha = topFile;
    }

    public boolean bottomUpAccess()
    {
        if(alpha == null)
        {
            return false;
        }

        if(alpha.isFile())
        {
            linkedHashSet.add(alpha);

            return true;
        }

        accessFilesAfter(alpha);

        return true;
    }

    // Called by bottomUpAccess when topFile is not null and is a directory
    private void accessFilesAfter(File topFile)
    {
        if(topFile.isDirectory())
        {
            ArrayList<File> regularFiles = new ArrayList<>();

            File files[] = topFile.listFiles();

            for(int i = 0; i < files.length; ++i)
            {
                if(files[i].isDirectory())
                {
                    accessFilesAfter(files[i]);
                }
                else if(files[i].isFile())
                {
                    regularFiles.add(files[i]);
                }
            }

            for(int i = 0; i < regularFiles.size(); ++i)
            {
                linkedHashSet.add(regularFiles.get(i));
            }
        }
    }

    public boolean topDownAccess()
    {
        if(alpha == null)
        {
            return false;
        }

        if(alpha.isFile())
        {
            linkedHashSet.add(alpha);

            return true;
        }

        accessFilesImmediate(alpha);

        return true;
    }

    // Called by topDownAccess when topFile is not null and is a directory
    private void accessFilesImmediate(File topFile)
    {
        if(topFile.isDirectory())
        {
            ArrayList<File> directories = new ArrayList<>();

            File files[] = topFile.listFiles();

            for(int i = 0; i < files.length; ++i)
            {
                if(files[i].isDirectory())
                {
                    directories.add(files[i]);

                }
                else if(files[i].isFile())
                {
                    linkedHashSet.add(files[i]);
                }
            }

            for(int i = 0; i < directories.size(); ++i)
            {
                accessFilesImmediate(directories.get(i));
            }
        }
    }

    // Getters and setters
    public LinkedHashSet<File> getLinkedHashSet()
    {
        return linkedHashSet;
    }

    public File getAlpha()
    {
        return alpha;
    }

    public void setAlpha(File topFile)
    {
        alpha = topFile;
    }
}

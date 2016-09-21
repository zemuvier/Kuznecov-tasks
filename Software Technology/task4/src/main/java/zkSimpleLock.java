import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class zkSimpleLock implements Watcher, Runnable
{

    ZooKeeper zk;
    String id;
    boolean lock = false;
    public zkSimpleLock()
    {
        try
        {
            zk = new ZooKeeper("127.0.0.1:2181", 5000, this);
            id = zk.create("/app/lock_", new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("Created lock : " + id);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Connection refused");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (KeeperException e)
        {
            e.printStackTrace();
        }
    }

    public boolean tryLock()
    {
        try
        {
            List<String> children = zk.getChildren("/app", false);
            Collections.sort(children);

            if (id.equals("/app/" + children.get(0)))
            {
                System.out.println("Lock aquired");
                return true;
            }
            else
            {
                System.out.println("Waiting for: " + children);
		//System.out.println(id.substring(5));
		zk.exists("/app/" + children.get(children.indexOf(id.substring(5)) - 1), true);
                return false;
            }

        }
        catch (KeeperException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return false;

    }

    public void process(WatchedEvent watchedEvent)
    {
	if(watchedEvent.getType() == Event.EventType.NodeDeleted && !this.lock)
		this.lock = tryLock();
    }

    public void run()
    {
        this.lock = tryLock();
        while(true);
    }

    public static void main(String[] args)
    {
        new zkSimpleLock().run();
    }
}
package com.ley.spring.customized.annotation.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;


/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/20 14:40
 * @describe file monitor
 */
@Slf4j
public class FileMonitor {

    /**
     * 文件夹目录
     **/
    private String path;

    /**
     * 需要监听的文件后缀
     **/
    private String fileSuffix;


    /**
     * 监听间隔
     **/
    private long interval = DEFAULT_INTERVAL;

    /**
     * 默认监听间隔10s
     **/
    private static final long DEFAULT_INTERVAL = 10 * 1000;


    /**
     * 文件监听器
     **/
    private FileAlterationListener listener;


    private FileAlterationMonitor monitor;


    public FileMonitor(String path, String fileSuffix, FileAlterationListenerAdaptor listenerAdaptor) {
        this.path = path;
        this.fileSuffix = fileSuffix;
        this.listener = listenerAdaptor;
        monitor = new FileAlterationMonitor(interval);
    }

    public FileMonitor(String path, String fileSuffix, long interval, FileAlterationListenerAdaptor listenerAdaptor) {
        this.path = path;
        this.fileSuffix = fileSuffix;
        this.interval = interval;
        this.listener = listenerAdaptor;
        monitor = new FileAlterationMonitor(interval);
    }

    /***
     * 开启监听
     */
    public void start() {
        if (path == null) {
            throw new IllegalStateException("Listen path must not be null");
        }
        if (listener == null) {
            throw new IllegalStateException("Listener must not be null");
        }

        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(fileSuffix));
        IOFileFilter filter = FileFilterUtils.or(directories, files);


        FileAlterationObserver observer = new FileAlterationObserver(path,
                filter);

        //观察者添加事件
        observer.addListener(listener);

        // 开启一个监视器
        // FileAlterationMonitor本身实现了Runnable,是单独的一个线程,按照设定的时间间隔运行,默认间隔是 10s
        //监视器添加观察者
        monitor.addObserver(observer);

        try {
            monitor.start();
        } catch (Exception e) {
            log.error("e: {}", e.getMessage());
        }
    }


    public void stop() throws Exception {
        monitor.stop(0L);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setListener(FileAlterationListener listener) {
        this.listener = listener;
    }


}

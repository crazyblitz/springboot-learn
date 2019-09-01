package com.ley.spring.customized.annotation.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.*;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/21 9:52
 * @describe
 */
@Slf4j
public class ContextRefresh {

    /**
     * 标准的环境资源
     **/
    private Set<String> standardSources = new HashSet<>(
            Arrays.asList(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
                    StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                    StandardServletEnvironment.JNDI_PROPERTY_SOURCE_NAME,
                    StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME,
                    StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME));

    private ConfigurableApplicationContext context;


    private static final String[] DEFAULT_PROPERTY_SOURCES = new String[]{
            CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME,
            "defaultProperties"};


    public ContextRefresh(ConfigurableApplicationContext context) {
        this.context = context;
    }


    public synchronized Set<String> refresh() {
        Map<String, Object> before = extract(
                this.context.getEnvironment().getPropertySources());
        addConfigFilesToEnvironment();
        Set<String> keys = changes(before,
                extract(this.context.getEnvironment().getPropertySources())).keySet();
        log.info("refresh keys: {}", keys);
        return keys;
    }


    private Map<String, Object> changes(Map<String, Object> before,
                                        Map<String, Object> after) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (String key : before.keySet()) {
            if (!after.containsKey(key)) {
                result.put(key, null);
            } else if (!equal(before.get(key), after.get(key))) {
                result.put(key, after.get(key));
            }
        }
        for (String key : after.keySet()) {
            if (!before.containsKey(key)) {
                result.put(key, after.get(key));
            }
        }
        return result;
    }

    private boolean equal(Object one, Object two) {
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        return one.equals(two);
    }


    private Map<String, Object> extract(MutablePropertySources propertySources) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<PropertySource<?>> sources = new ArrayList<PropertySource<?>>();
        for (PropertySource<?> source : propertySources) {
            sources.add(0, source);
        }
        for (PropertySource<?> source : sources) {
            if (!this.standardSources.contains(source.getName())) {
                extract(source, result);
            }
        }
        return result;
    }

    private void extract(PropertySource<?> parent, Map<String, Object> result) {
        if (parent instanceof CompositePropertySource) {
            try {
                List<PropertySource<?>> sources = new ArrayList<PropertySource<?>>();
                for (PropertySource<?> source : ((CompositePropertySource) parent)
                        .getPropertySources()) {
                    sources.add(0, source);
                }
                for (PropertySource<?> source : sources) {
                    extract(source, result);
                }
            } catch (Exception e) {
                return;
            }
        } else if (parent instanceof EnumerablePropertySource) {
            for (String key : ((EnumerablePropertySource<?>) parent).getPropertyNames()) {
                result.put(key, parent.getProperty(key));
            }
        }
    }


    ConfigurableApplicationContext addConfigFilesToEnvironment() {
        ConfigurableApplicationContext capture = null;
        try {
            StandardEnvironment environment = copyEnvironment(
                    this.context.getEnvironment());
            SpringApplicationBuilder builder = new SpringApplicationBuilder(Empty.class)
                    .bannerMode(Banner.Mode.OFF).web(false).environment(environment);

//            Properties properties = FileListener.getProperties();
//            builder.properties(properties);

            // Just the listeners that affect the environment (e.g. excluding logging
            // listener because it has side effects)

            ConfigFileApplicationListener listener = new ConfigFileApplicationListener();
            listener.setSearchLocations("file:D:/workspace/idea workspace/springboot-learn/spring-customized-annotation/src/main/resources/");
            listener.setSearchNames("application");
            builder.application()
                    .setListeners(Arrays.asList(listener));

            capture = builder.run();
            MutablePropertySources target = this.context.getEnvironment()
                    .getPropertySources();
            String targetName = null;
            for (PropertySource<?> source : environment.getPropertySources()) {
                String name = source.getName();
                if (target.contains(name)) {
                    targetName = name;
                }
                if (!this.standardSources.contains(name)) {
                    if (target.contains(name)) {
                        target.replace(name, source);
                    } else {
                        if (targetName != null) {
                            target.addAfter(targetName, source);
                        } else {
                            // targetName was null so we are at the start of the list
                            target.addFirst(source);
                            targetName = name;
                        }
                    }
                }
            }
        } finally {
            ConfigurableApplicationContext closeable = capture;
            while (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    // Ignore;
                }
                if (closeable.getParent() instanceof ConfigurableApplicationContext) {
                    closeable = (ConfigurableApplicationContext) closeable.getParent();
                } else {
                    break;
                }
            }
        }
        return capture;
    }


    private StandardEnvironment copyEnvironment(ConfigurableEnvironment input) {
        StandardEnvironment environment = new StandardEnvironment();
        MutablePropertySources capturedPropertySources = environment.getPropertySources();
        // Only copy the default property source(s) and the profiles over from the main
        // environment (everything else should be pristine, just like it was on startup).
        for (String name : DEFAULT_PROPERTY_SOURCES) {
            if (input.getPropertySources().contains(name)) {
                if (capturedPropertySources.contains(name)) {
                    capturedPropertySources.replace(name,
                            Objects.requireNonNull(input.getPropertySources().get(name)));
                } else {
                    capturedPropertySources.addLast(Objects.requireNonNull(input.getPropertySources().get(name)));
                }
            }
        }
        environment.setActiveProfiles(input.getActiveProfiles());
        environment.setDefaultProfiles(input.getDefaultProfiles());
        Map<String, Object> map = new HashMap<>();
        map.put("spring.jmx.enabled", false);
        map.put("spring.main.sources", "");
        return environment;
    }


    @Configuration
    protected static class Empty {

    }
}

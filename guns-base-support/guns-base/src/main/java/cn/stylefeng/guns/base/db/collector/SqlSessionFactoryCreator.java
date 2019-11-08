package cn.stylefeng.guns.base.db.collector;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * mybatis的一些配置收集
 *
 * @author fengshuonan
 * @date 2019-06-15-18:30
 */
@Data
@Slf4j
@Configuration
public class SqlSessionFactoryCreator {

    private final MybatisPlusProperties properties;

    private final Interceptor[] interceptors;

    private final DatabaseIdProvider databaseIdProvider;

    private final ApplicationContext applicationContext;

    private final ResourceLoader resourceLoader;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    public SqlSessionFactoryCreator(MybatisPlusProperties properties,
                                    ResourceLoader resourceLoader,
                                    ObjectProvider<Interceptor[]> interceptorsProvider,
                                    ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                    ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
                                    ApplicationContext applicationContext) {
        this.properties = properties;
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.applicationContext = applicationContext;
        this.resourceLoader = resourceLoader;
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }

    /**
     * 创建SqlSessionFactory
     */
    public synchronized SqlSessionFactory createSqlSessionFactory(DataSource dataSource) {
        try {
            MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
            factory.setDataSource(dataSource);
            factory.setVfs(SpringBootVFS.class);
            if (StringUtils.hasText(this.properties.getConfigLocation())) {
                factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
            }
            if (this.properties.getConfigurationProperties() != null) {
                factory.setConfigurationProperties(this.properties.getConfigurationProperties());
            }
            if (!ObjectUtils.isEmpty(this.interceptors)) {
                factory.setPlugins(this.interceptors);
            }
            if (this.databaseIdProvider != null) {
                factory.setDatabaseIdProvider(this.databaseIdProvider);
            }
            if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
                factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
            }
            // TODO 自定义枚举包
            if (StringUtils.hasLength(this.properties.getTypeEnumsPackage())) {
                factory.setTypeEnumsPackage(this.properties.getTypeEnumsPackage());
            }
            if (this.properties.getTypeAliasesSuperType() != null) {
                factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
            }
            if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
                factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
            }
            if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
                factory.setMapperLocations(this.properties.resolveMapperLocations());
            }
            // TODO 此处必为非 NULL
            GlobalConfig globalConfig = this.properties.getGlobalConfig();
            //注入填充器
            if (this.applicationContext.getBeanNamesForType(MetaObjectHandler.class,
                    false, false).length > 0) {
                MetaObjectHandler metaObjectHandler = this.applicationContext.getBean(MetaObjectHandler.class);
                globalConfig.setMetaObjectHandler(metaObjectHandler);
            }
            //注入主键生成器
            if (this.applicationContext.getBeanNamesForType(IKeyGenerator.class, false,
                    false).length > 0) {
                IKeyGenerator keyGenerator = this.applicationContext.getBean(IKeyGenerator.class);
                globalConfig.getDbConfig().setKeyGenerator(keyGenerator);
            }
            //注入sql注入器
            if (this.applicationContext.getBeanNamesForType(ISqlInjector.class, false,
                    false).length > 0) {
                ISqlInjector iSqlInjector = this.applicationContext.getBean(ISqlInjector.class);
                globalConfig.setSqlInjector(iSqlInjector);
            }

            //globalConfig中有缓存sqlSessionFactory，目前还没别的办法
            SqlSessionFactory sqlSessionFactory = factory.getObject();
            globalConfig.signGlobalConfig(sqlSessionFactory);

            factory.setGlobalConfig(globalConfig);
            return factory.getObject();
        } catch (Exception e) {
            log.error("初始化SqlSessionFactory错误！", e);
            throw new ServiceException(500, "初始化SqlSessionFactory错误！");
        }
    }

}

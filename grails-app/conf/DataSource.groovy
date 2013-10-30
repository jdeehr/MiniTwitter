         /*
dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}      */

dataSource {
    pooled = true
    driverClassName = "net.sourceforge.jtds.jdbc.Driver"
    dialect = "org.hibernate.dialect.SQLServerDialect"
}


hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
              /*
            dataSource {
                dbCreate = "update"
                url = "jdbc:h2:file:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            }   */

            url = "jdbc:jtds:sqlserver://127.0.0.1:1433/MiniTwitter_test;instance=SQLEXPRESS;user=tester2;password=tester2"

        }
    }
    test {

               dataSource {

                   //dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
                   url = "jdbc:jtds:sqlserver://127.0.0.1:1433/MiniTwitter_test;instance=SQLEXPRESS;user=tester2;password=tester2"
               } /*
               dataSource {
                   dbCreate = "create-drop"
                   url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
               }   */
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:jtds:sqlserver://127.0.0.1:1433/MiniTwitter_dev;instance=SQLEXPRESS;user=JOEL-HP\\JOEL;password=d0nts8ck"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}

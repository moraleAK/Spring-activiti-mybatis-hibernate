package database.aspects;

import org.aspectj.lang.annotation.Aspect;

/**
 * User: Rolandz
 * Date: 5/24/16
 * Time: 3:56 PM
 */
@Aspect
public class UseReadOnlyDataSourceAspect {
   /* private static Logger LOG = LoggerFactory.getLogger(UseReadOnlyDataSourceAspect.class);

    @Pointcut("@annotation(com.el.wc.payment.commons.annotations.UseReadOnlyDataSource)")
    public void doPointCut() { }

    @Before("@annotation(com.el.wc.payment.commons.annotations.UseReadOnlyDataSource)")
    public void doBefore(JoinPoint joinPoint) {
        LOG.debug("Using read only datasource for {}", joinPoint);
        DataSourceSelector.setDataSource(DataSourceSelector.READ);
    }

    @After("@annotation(com.el.wc.payment.commons.annotations.UseReadOnlyDataSource)")
    public void doAfter(JoinPoint joinPoint) {
        LOG.debug("Reverting datasource after {}", joinPoint);
        DataSourceSelector.clearDataSource();
    }*/
}

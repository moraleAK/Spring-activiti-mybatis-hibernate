package database.aspects;

import org.aspectj.lang.annotation.Aspect;

/**
 * User: Rolandz
 * Date: 5/24/16
 * Time: 3:53 PM
 */
@Aspect
public class UseReadWriteDataSourceAspect {
/*    private static Logger LOG = LoggerFactory.getLogger(UseReadWriteDataSourceAspect.class);

    @Pointcut("@annotation(com.el.wc.payment.commons.annotations.UseReadWriteDataSource)")
    public void doPointCut() { }

    @Before("@annotation(com.el.wc.payment.commons.annotations.UseReadWriteDataSource)")
    public void doBefore(JoinPoint joinPoint) {
        LOG.debug("Using read write datasource for" + joinPoint);
        DataSourceSelector.setDataSource(DataSourceSelector.WRITE);

    }

    @After("@annotation(com.el.wc.payment.commons.annotations.UseReadWriteDataSource)")
    public void doAfter(JoinPoint joinPoint) {
        LOG.debug("Reverting datasource after" + joinPoint);
        DataSourceSelector.clearDataSource();
    }*/
}

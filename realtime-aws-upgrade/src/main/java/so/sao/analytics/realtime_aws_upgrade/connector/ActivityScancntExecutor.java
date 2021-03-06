package so.sao.analytics.realtime_aws_upgrade.connector;

import com.amazonaws.services.kinesis.connectors.KinesisConnectorRecordProcessorFactory;
import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import so.sao.analytics.realtime_aws_upgrade.connector.pipeline.ActivityScancntPipeline;
import so.sao.analytics.realtime_aws_upgrade.kinesis.KinesisConnectorExecutor;
import so.sao.analytics.realtime_aws_upgrade.model.ActivityCount;

/**
 * Executor to emit Activity records to Apache HBase running on Amazon EMR. The number of records per Apache HBase put operation can be set in the buffer
 * properties.
 */
public class ActivityScancntExecutor extends KinesisConnectorExecutor<FlatActivity, ActivityCount> {

	// properties streamName
	private static final String streamName = "kinesisActivitiesStream";
	// properties appName
	private static final String appName = "activityScancntAppName";
	
    /**
     * Creates a new ActivityScancntExecutor.
     * 
     * @param configFile
     *        The name of the configuration file to look for on the classpath
     */
    public ActivityScancntExecutor(String configFile) {
        super(configFile,streamName,appName);
    }

    @Override
    public KinesisConnectorRecordProcessorFactory<FlatActivity, ActivityCount> getKinesisConnectorRecordProcessorFactory() {
        return new KinesisConnectorRecordProcessorFactory<FlatActivity, ActivityCount>(
                new ActivityScancntPipeline(), config);
    }
}

package com.bdjw.coprocessor;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*ES客户端*/
public class ESClient {
    public static String clustername;
    public static String clusterhost;
    public static String clusterport;
    public static String esindex;
    public static String estype;

    public static String getInfo() {
        List<String> fields = new ArrayList<String>();
        try {
            for (Field f : ESClient.class.getDeclaredFields()) {
                fields.add(f.getName() + "=" + f.get(null));
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return StringUtils.join(fields, ", ");
    }

    public static void initEsClient() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", ESClient.clusterName).build();
        client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(
                        ESClient.nodeHost, ESClient.nodePort));
    }

    public static void closeEsClient() {
        client.close();
    }
}

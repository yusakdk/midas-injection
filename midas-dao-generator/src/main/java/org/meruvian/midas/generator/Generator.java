package org.meruvian.midas.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "org.meruvian.midas.injection.repository.db");

        addNews(schema);

        new DaoGenerator().generateAll(schema, "midas-injection/src/main/java");
    }

    private static void addNews(Schema schema){
        Entity news = addDefaultPersistence(schema.addEntity("News"));

        news.addStringProperty("title");
        news.addStringProperty("content");
//        news.addLongProperty("createDate");
        news.addIntProperty("status");
    }

    private static Entity addDefaultPersistence(Entity entity) {
        entity.implementsSerializable();
        entity.implementsInterface("LogInformationAware");
        entity.addLongProperty("dbId").columnName("_id").primaryKey().autoincrement();
        entity.addDateProperty("dbCreateDate").columnName("create_date");
        entity.addDateProperty("dbUpdateDate").columnName("update_date");
        entity.addStringProperty("dbCreateBy").columnName("create_by");
        entity.addStringProperty("dbUpdateBy").columnName("update_by");
        entity.addIntProperty("dbActiveFlag").columnName("active_flag");
        entity.addStringProperty("id").columnName("ref_id");
        entity.addDateProperty("refCreateDate");
        entity.addStringProperty("refCreateBy");

        return entity;
    }
}

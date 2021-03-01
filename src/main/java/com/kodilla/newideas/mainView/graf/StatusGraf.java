package com.kodilla.newideas.mainView.graf;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.service.DbService;
import io.quickchart.QuickChart;
import org.springframework.beans.factory.annotation.Autowired;


public class StatusGraf {

    @Autowired
    DbService dbService;

    @Autowired
    IdeaController ideaController;


    public String createGraf(DbService dbService) {

        String reported = String.valueOf(dbService.getAllIdeas().stream()
                .map(r -> r.getStatus())
                .map(t -> t.getNotificationStatus())
                .filter(r -> r.contains("reported")).count()
        );

        String implemented = String.valueOf(dbService.getAllIdeas().stream()
                .map(r -> r.getStatus())
                .map(t -> t.getNotificationStatus())
                .filter(r -> r.contains("implemented")).count()
        );

        String inProgress = String.valueOf(dbService.getAllIdeas().stream()
                .map(r -> r.getStatus())
                .map(t -> t.getNotificationStatus())
                .filter(r -> r.contains("in progress")).count()
        );

        String canceled = String.valueOf(dbService.getAllIdeas().stream()
                .map(r -> r.getStatus())
                .map(t -> t.getNotificationStatus())
                .filter(r -> r.contains("canceled")).count()
        );


        QuickChart chart = new QuickChart();
        chart.setWidth(500);
        chart.setHeight(300);
        chart.setConfig("{"
                + "    type: 'pie',"
                + "    data: {"
                + "        labels: ['reported', 'in progres', 'implemented', 'canceled'],"
                + "        datasets: [{"
                + "            label: 'Statuses',"
                + "            data: [" + reported + "," + inProgress + ","+ implemented+"," + canceled+"]"
                + "        }]"
                + "    }"
                + "}"
        );

        String url = chart.getUrl();

        System.out.println(chart.getUrl());
        return url;
    }
}

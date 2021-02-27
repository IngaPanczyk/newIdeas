package com.kodilla.newideas.mainView.graf;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.service.DbService;
import io.quickchart.QuickChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class StatusGraf {

    @Autowired
    DbService dbService;

    @Autowired
    IdeaController ideaController;


    //long reported = dbService.getAllStatuses().stream().filter(r->r.getNotificationStatus().contains("reported")).count();
    //long reported = ideaController.getIdeas().stream().filter(r->r.getStatus().getNotificationStatus().contains("reported")).count();

    //long reported = 3L;
    //String repo = String.valueOf(reported);


    public String createGraf(DbService dbService) {

        String reported = String.valueOf(dbService.getAllStatuses().stream()
                .filter(r -> r.getNotificationStatus().contains("reported")).count()
        );
        String inProgres = String.valueOf(dbService.getAllStatuses().stream()
                .filter(r -> r.getNotificationStatus().contains("in progres")).count()
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
                + "            data: [" + reported + "," + inProgres + ", 70, 180]"
                + "        }]"
                + "    }"
                + "}"
        );

        String url = chart.getUrl();

        System.out.println(chart.getUrl());
        return url;
    }
}

package org.example.entities;
import java.util.*;
import java.time.*;
import java.sql.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Train {
    private String trainId;
    private String trianNo;
    private List<List<Integer>> seats;
    private HashMap<String, Time> stationTimes;
    private List<String> stations;
}

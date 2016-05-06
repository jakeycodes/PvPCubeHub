/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.pvpcube.hub.events.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;


public class WeatherChange implements Listener{

    
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        if(e.toWeatherState()){
            e.setCancelled(true);
        }
    }
}

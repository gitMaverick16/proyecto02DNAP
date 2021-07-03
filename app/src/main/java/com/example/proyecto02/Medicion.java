package com.example.proyecto02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Medicion extends AppCompatActivity implements SensorEventListener{

    TextView texto;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private float valormax;
    String opcionSeleccionada;


    //Variables diego
    private final static int NOTIFICATION_ID=0;
    SensorManager sensorManagerP;
    Sensor sensor_proximidad;
    int contador=0;
    double prox_distancia=0, luz=0;
    TextView proximidad, distancia;
    String str_proximidad="LEJOS";

    private SensorEventListener proximityEventListener;
    //fin variables Diego


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion);

        texto = (TextView)findViewById(R.id.texto);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        opcionSeleccionada=getIntent().getStringExtra("opcionKey");
        valormax=lightSensor.getMaximumRange();


        //intancia de sensores Diego
        //inicio de sensormanager
        sensorManagerP= (SensorManager)getSystemService(SENSOR_SERVICE);
        //definiciom de los sensores usados
        sensor_proximidad=sensorManagerP.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //fin de instancia de sensores diego


        lightEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                texto.setText("");
                texto.append("\n" + "Luminosidad: " + value+"\n"+opcionSeleccionada);
                if(opcionSeleccionada.equals("1")){
                    if(value>=100 && value<=200){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con iluminacion recomendada");
                    }else if(value<100){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con muy poca iluminacion");
                    }else{
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con mucha iluminacion");
                    }
                }else if(opcionSeleccionada.equals("2")){
                    if(value>=250 && value<=300){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con iluminacion recomendada");
                    }else if(value<250){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con muy poca iluminacion");
                    }else{
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con mucha iluminacion");
                    }
                }else if(opcionSeleccionada.equals("3")){
                    if(value>=450 && value<=550){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con iluminacion recomendada");
                    }else if(value<450){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con muy poca iluminacion");
                    }else{
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con mucha iluminacion");
                    }
                }else if(opcionSeleccionada.equals("4")){
                    if(value>=500 && value<=700){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con iluminacion recomendada");
                    }else if(value<500){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con muy poca iluminacion");
                    }else{
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con mucha iluminacion");
                    }
                }else if(opcionSeleccionada.equals("5")){
                    if(value>=950 && value<=1050){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con iluminacion recomendada");
                    }else if(value<950){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con muy poca iluminacion");
                    }else{
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con mucha iluminacion");
                    }
                }else if(opcionSeleccionada.equals("6")){
                    if(value>=1500 && value<=2000){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con iluminacion recomendada");
                    }else if(value<1500){
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con muy poca iluminacion");
                    }else{
                        texto.setText("");
                        texto.append("\n" + "Luminosidad: " + value+"\n"+"Estas con mucha iluminacion");
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            prox_distancia=event.values[0];
            //distancia.setText("");
            //distancia.append(""+prox_distancia);

            if(prox_distancia<1){
                //str_proximidad="CERCA";
                createNotificationChannel();
       //         crearNotificacion();

            }else{
                //str_proximidad="LEJOS";
            }
            //proximidad.setText("");
            //proximidad.append(""+str_proximidad);

        }
        contador++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManagerP.registerListener(this, sensor_proximidad, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
        sensorManagerP.unregisterListener(this);
    }


    private void createNotificationChannel(){
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        long[] vibrationWaveFormDurationPattern = {1000, 1000, 1000};

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            VibrationEffect vibrationEffect = VibrationEffect.createWaveform(vibrationWaveFormDurationPattern, -1);

            // it is safe to cancel all the vibration taking place currently
            vibrator.cancel();

            // now initiate the vibration of the device
            vibrator.vibrate(vibrationEffect);
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel("NOTIFICACION", name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    /*
    private void crearNotificacion(){
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(), "NOTIFICACION");
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        //builder.setSmallIcon(R.drawable.ic_warning);
        builder.setContentTitle("APP PROYECTO DANP");
        //builder.setContentText("El dispositivo esta boca abajo");
        //builder.setColor(Color.CYAN);
        //builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        //builder.setDefaults(Notification.DEFAULT_SOUND);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

    }*/
}
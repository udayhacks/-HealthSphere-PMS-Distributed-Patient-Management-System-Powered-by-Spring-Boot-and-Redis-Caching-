
package com.pm.patient_service.kafka;
import com.pm.patient_service.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;


@Service
public class KafkaProducer {
    // these variable act as ResponseEntity as for kafka  sending key as string and value as byte-array;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private  static  final Logger log =  LoggerFactory.getLogger(KafkaProducer.class);
    public KafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public  void  sendEvent(Patient patient) {
        //log.info("kafka message is started");
        //
        PatientEvent patientEvent = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATE")
                .build();
        try {
            kafkaTemplate.send("patient",patientEvent.toByteArray());
            //log.info("kafka message is send");

        } catch (Exception e) {
           log.error("Error sending PatientCreated {}",patientEvent);
        }

    }

}

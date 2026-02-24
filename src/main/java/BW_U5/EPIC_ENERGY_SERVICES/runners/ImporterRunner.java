package BW_U5.EPIC_ENERGY_SERVICES.runners;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Comune;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Provincia;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ComuneRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ProvinciaRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;

@Component
public class ImporterRunner implements CommandLineRunner {
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    @Override
    public void run(String... args) throws Exception {

        try{
            CSVReader lettore = new CSVReaderBuilder(new FileReader("D:\\EPICODE\\UNIT-5\\BW_U5_EPIC_ENERGY_SERVICES\\src\\main\\resources\\province-italiane.csv"))
                    .withSkipLines(1)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

            String[] riga;
            while ((riga = lettore.readNext()) != null) {
                Provincia provincia = new Provincia(riga[0], riga[1], riga[2]);
//                provinciaRepository.save(provincia);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }

        try{
            CSVReader lettore = new CSVReaderBuilder(new FileReader("D:\\EPICODE\\UNIT-5\\BW_U5_EPIC_ENERGY_SERVICES\\src\\main\\resources\\comuni-italiani.csv"))
                    .withSkipLines(1)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

            String[] riga;
            while((riga = lettore.readNext()) != null) {
                Provincia provinciaAssociata = provinciaRepository.findByProvincia(riga[3]).orElseThrow(()->NotFoundException("La provincia cercata non esiste!"));
                Comune comune = new Comune(riga[2],provinciaAssociata);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
    }
}

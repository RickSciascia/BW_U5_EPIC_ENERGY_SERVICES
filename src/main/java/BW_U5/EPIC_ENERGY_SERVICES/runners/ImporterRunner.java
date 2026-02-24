package BW_U5.EPIC_ENERGY_SERVICES.runners;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Comune;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Provincia;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
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

		if (provinciaRepository.count() == 0) {
			try {
				CSVReader lettore = new CSVReaderBuilder(new FileReader("province-italiane.csv"))
						.withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

				String[] riga;
				while ((riga = lettore.readNext()) != null) {
					Provincia provincia = new Provincia(riga[0], riga[1], riga[2]);
					provinciaRepository.save(provincia);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ex.getMessage();
			}

			try {
				CSVReader lettore = new CSVReaderBuilder(new FileReader("comuni-italiani.csv"))
						.withSkipLines(1)
						.withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

				String[] riga;
				while ((riga = lettore.readNext()) != null) {
					if (riga[3].equals("Verbano-Cusio-Ossola")) riga[3] = ("Verbania");
					if (riga[3].equals("Valle d'Aosta/Vallée d'Aoste")) riga[3] = ("Aosta");
					if (riga[3].equals("Monza e della Brianza")) riga[3] = ("Monza-Brianza");
					if (riga[3].equals("Bolzano/Bozen")) riga[3] = ("Bolzano");
					if (riga[3].equals("La Spezia")) riga[3] = ("La-Spezia");
					if (riga[3].equals("Reggio nell'Emilia")) riga[3] = ("Reggio-Emilia");
					if (riga[3].equals("Forlì-Cesena")) riga[3] = ("Forli-Cesena");
					if (riga[3].equals("Pesaro e Urbino")) riga[3] = ("Pesaro-Urbino");
					if (riga[3].equals("Ascoli Piceno")) riga[3] = ("Ascoli-Piceno");
					if (riga[3].equals("Reggio Calabria")) riga[3] = ("Reggio-Calabria");
					if (riga[3].equals("Vibo Valentia")) riga[3] = ("Vibo-Valentia");
					if (riga[3].equals("Sud Sardegna")) riga[3] = ("Carbonia Iglesias");
					Provincia provinciaAssociata = provinciaRepository.findByProvincia(riga[3]).orElseThrow(() -> new NotFoundException("Problema con la Provincia letta dal File CSV, Non esiste nessuna provincia in DB con quel nome!"));
					Comune comune = new Comune(riga[2], provinciaAssociata);
					comuneRepository.save(comune);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ex.getMessage();
			}
		}

	}
}

package com.azienda.progetto.businessLogic;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.azienda.progetto.model.Film;
import com.azienda.progetto.model.Prenotazione;
import com.azienda.progetto.model.Ruolo;
import com.azienda.progetto.model.Sala;
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.model.Utente;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class BusinessLogic {
	
	private EntityManager em;
	private UtenteDao utenteDao;
	private RuoloDao ruoloDao;
	private PrenotazioneDao prenotazioneDao;
	private SalaDao salaDao;
	private SpettacoloDao spettacoloDao;
	private FilmDao filmDao;
	
	public BusinessLogic(EntityManager em, UtenteDao utenteDao, RuoloDao ruoloDao, PrenotazioneDao prenotazioneDao,
			SalaDao salaDao, SpettacoloDao spettacoloDao, FilmDao filmDao) {
		
		this.em = em;
		this.utenteDao = utenteDao;
		this.ruoloDao = ruoloDao;
		this.prenotazioneDao = prenotazioneDao;
		this.salaDao = salaDao;
		this.spettacoloDao = spettacoloDao;
		this.filmDao = filmDao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public UtenteDao getUtenteDao() {
		return utenteDao;
	}

	public void setUtenteDao(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}

	public RuoloDao getRuoloDao() {
		return ruoloDao;
	}

	public void setRuoloDao(RuoloDao ruoloDao) {
		this.ruoloDao = ruoloDao;
	}

	public PrenotazioneDao getPrenotazioneDao() {
		return prenotazioneDao;
	}

	public void setPrenotazioneDao(PrenotazioneDao prenotazioneDao) {
		this.prenotazioneDao = prenotazioneDao;
	}

	public SalaDao getSalaDao() {
		return salaDao;
	}

	public void setSalaDao(SalaDao salaDao) {
		this.salaDao = salaDao;
	}

	public SpettacoloDao getSpettacoloDao() {
		return spettacoloDao;
	}

	public void setSpettacoloDao(SpettacoloDao spettacoloDao) {
		this.spettacoloDao = spettacoloDao;
	}

	public FilmDao getFilmDao() {
		return filmDao;
	}

	public void setFilmDao(FilmDao filmDao) {
		this.filmDao = filmDao;
	}
	
	public Utente login(String username, String password) throws Exception{		
		
			List<Utente> utenti = utenteDao.findByUsernameAndPassword(username, password);
			
			if (utenti.isEmpty()) {
				return null;
			}else if(utenti.size()==1) {
				return utenti.get(0);
			} else {
				throw new Exception();
			}	
		
	}
	
	public String registrazione (String user, String pw, String nome, String cognome,
			LocalDate dataNascita, String email) {
		
			try {
				em.getTransaction().begin();
				Utente utente = new Utente(nome,cognome,dataNascita,email,user,pw,null);
				
				utenteDao.create(utente);
				
				em.getTransaction().commit();
				
				return "ok";				
			}
			catch(PersistenceException sqlEx) {
				em.getTransaction().rollback();
				sqlEx.printStackTrace();				
				return "riprova";
			}
			
			catch (Exception e ) {
				em.getTransaction().rollback();
				e.printStackTrace();
				return "erroreGenerico";
			}					
		}
	
	public String modifica(Utente utente, String password, String nome, String cognome, String email) {
		
		try {
			em.getTransaction().begin();
			utente.setPassword(password);
			utente.setNome(nome);
			utente.setCognome(cognome);			
			utente.setEmail(email);		
			
			utenteDao.update(utente);			
			em.getTransaction().commit();
			
			return "ok";				
		}
		catch(PersistenceException sqlEx) {
			em.getTransaction().rollback();
			sqlEx.printStackTrace();				
			return "riprova";
		}
		
		catch (Exception e ) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return "erroreGenerico";
		}		
		
	}
	
	public String elimina(Utente u) {
		try {
			em.getTransaction().begin();
			
			List<Prenotazione> prenotazioniUtente = prenotazioneDao.findByUtente(u);
			
			for(int i=prenotazioniUtente.size()-1 ; i>=0 ;i--) {
				prenotazioniUtente.get(i).getUtente().getPrenotazioni().remove(prenotazioniUtente.get(i));
				prenotazioniUtente.get(i).getSpettacolo().getPrenotazioni().remove(prenotazioniUtente.get(i));
				prenotazioneDao.delete(prenotazioniUtente.get(i));
			}
			
			utenteDao.delete(u);
			
			em.getTransaction().commit();
			return "ok";
		}catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return "errore";
		}
	}
	
	public String diventaUtente(Utente u) {
		try {
			em.getTransaction().begin();
			
			u.setRuolo(em.find(Ruolo.class,3));
			utenteDao.update(u);
			
			em.getTransaction().commit();
			return "ok";
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return "errore";
		}
	}
	
	public String diventaStaff(Utente u) {
		try {
			em.getTransaction().begin();
			
			u.setRuolo(em.find(Ruolo.class,2));
			utenteDao.update(u);
			
			em.getTransaction().commit();
			return "ok";
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return "errore";
		}
	}
	
	public String effettuaPrenotazione(Utente u, Spettacolo s,Integer postiPren) {
		try {
			Integer totPostiPrenotati = 0;
			for(int i = 0; i<s.getPrenotazioni().size(); i++) {
				totPostiPrenotati += s.getPrenotazioni().get(i).getPostiPrenotati();
			}
			
			
			if (s.getSala().getNumeroPosti()>= totPostiPrenotati+postiPren) {
				
				
				em.getTransaction().begin();
				
				Prenotazione nuova = new Prenotazione(s,postiPren,u);
				nuova.setUtente(u);
				nuova.setSpettacolo(s);
				u.getPrenotazioni().add(nuova);			
				s.getPrenotazioni().add(nuova);			
				
				prenotazioneDao.create(nuova);
				
				em.getTransaction().commit();
				return "ok";
			} else {
				
				return "postiNonDisponibili";
			}
			
			
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return "erroreGenerico";
		}
	}
	
	public String inserisciFilm (String titolo, LocalTime durata, String genere, String trama,String trailer) {
		
		try {
			em.getTransaction().begin();
			
			Film film = new Film(titolo,durata,genere,trama,trailer);
			filmDao.create(film);
			
			em.getTransaction().commit();
			return "ok";
		}catch(PersistenceException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return "tooLong";
		}
		
		catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return "erroreGenerico";
		}
		
	}
	
	public String eliminaFilm(Film film) {
		try {
			
			List<Spettacolo> lista = spettacoloDao.findByTitolo(film.getTitolo());
			for (int i=0; i< lista.size();i++) {
				eliminaSpettacolo(lista.get(i));
			}
			em.getTransaction().begin();
			filmDao.delete(film);
			em.getTransaction().commit();
			return "ok";
		} catch (Exception e) {
			em.getTransaction().rollback();
			return "errore";
		}
	}
	
	public String modificaFilm(Film film,String titolo, String genere, LocalTime durata, String trama, String trailer) {
		try {
			em.getTransaction().begin();		
			film.setTitolo(titolo);
			film.setGenere(genere);
			film.setDurata(durata);
			film.setTrama(trama);
			film.setTrailer(trailer);
			
			filmDao.update(film);
			em.getTransaction().commit();
			return "ok";
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			return "errore";
		}
	}
	
	public String aggiungiSala(String nome, Integer numeroP) {
		try {
			em.getTransaction().begin();
			
			Sala sala = new Sala(nome, numeroP);
			salaDao.create(sala);
			
			em.getTransaction().commit();
			return "ok";
		} catch(PersistenceException e) {
			
			em.getTransaction().rollback();
			return "nomeSala";
		
		}catch (Exception e) {
			
			em.getTransaction().rollback();
			return "errore";
		}
	}
	
	public String eliminaSala(Sala sala) {
		try {
			
			
			List<Spettacolo> lista = spettacoloDao.findBySala(sala);
			
			for (int i=0; i< lista.size();i++) {
				eliminaSpettacolo(lista.get(i));
			}
			em.getTransaction().begin();
			salaDao.delete(sala);
			em.getTransaction().commit();
			return "ok";
		} catch (Exception e) {
			em.getTransaction().rollback();
			return "errore";
		}
	}
	
	public String modificaSala(Sala sala, String nome, Integer numeroP) {
		try {
			em.getTransaction().begin();		
			sala.setNome(nome);
			sala.setNumeroPosti(numeroP);
			salaDao.update(sala);
			
			em.getTransaction().commit();
			return "ok";
		}catch(PersistenceException e) {
			
			em.getTransaction().rollback();
			return "nomeSala";
		
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			return "errore";
		}
	}
	
	public String aggiungiSpettacolo(LocalDateTime dataOra, Film film, Sala sala) {
		try {
			
			
			List<Spettacolo> spettacoli = spettacoloDao.retrieveSortedByDataAndSala(sala);		
			
			
			
			if(film!=null && sala!= null && dataOra != null) {
				
				LocalDateTime inizioTutti = null;
				LocalDateTime fineTutti = null;
				
				LocalDateTime inizioNuovo = dataOra;
				LocalDateTime fineNuovo = dataOra.plusHours(film.getDurata().getHour()).plusMinutes(film.getDurata().getMinute()).plusMinutes(30L);				
				
				
				if(spettacoli.isEmpty()) {
					em.getTransaction().begin();
					Spettacolo spettacolo = new Spettacolo(dataOra,film,sala);
					
					spettacoloDao.create(spettacolo);
					em.getTransaction().commit();
					return "ok";
				}
				
				for (int i = 0 ; i<spettacoli.size();i++) {					
						
						 
						fineTutti = spettacoli.get(i).getDataSpettacolo().plusHours(spettacoli.get(i).getFilm().getDurata().getHour()).plusMinutes(spettacoli.get(i).getFilm().getDurata().getMinute()).plusMinutes(30L);
						
						
						if(i==0) {
							inizioTutti = spettacoli.get(i).getDataSpettacolo();
							if(fineNuovo.isBefore(inizioTutti)) {
								
								em.getTransaction().begin();
								Spettacolo spettacolo = new Spettacolo(dataOra,film,sala);
								
								spettacoloDao.create(spettacolo);
								em.getTransaction().commit();
								return "ok";								
							} 
						}
						
						
						if(i== spettacoli.size()-1 ) {
							
							if(spettacoli.size()==1) {
								inizioTutti = spettacoli.get(i).getDataSpettacolo();
								if(inizioNuovo.isAfter(fineTutti) || fineNuovo.isBefore(inizioTutti)) {
									em.getTransaction().begin();
									Spettacolo spettacolo = new Spettacolo(dataOra,film,sala);
									
									spettacoloDao.create(spettacolo);
									em.getTransaction().commit();
									return "ok";
								}
							}
							if(inizioNuovo.isAfter(fineTutti)) {
								em.getTransaction().begin();
								Spettacolo spettacolo = new Spettacolo(dataOra,film,sala);
								
								spettacoloDao.create(spettacolo);
								em.getTransaction().commit();
								return "ok";	
							}
						} 
						else if(i!=spettacoli.size()-1) {
							inizioTutti = spettacoli.get(i+1).getDataSpettacolo();
						}				
						
						
						
						if(inizioNuovo.isAfter(fineTutti)) {
							if(fineNuovo.isBefore(inizioTutti)) {
								em.getTransaction().begin();
								Spettacolo spettacolo = new Spettacolo(dataOra,film,sala);
								
								spettacoloDao.create(spettacolo);
								em.getTransaction().commit();
								return "ok";								
							} 
						} 
					} 					
					
				
			} else {
				return "deviInserireTuttiICampi";
			}			
			
			
			
			return "sovrapposizione";		
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			em.getTransaction().rollback();
			return "errore";		
			
		}
		
		
		
	}
	
	public String eliminaSpettacolo(Spettacolo spettacolo ) {
		
		
		try {
			
			eliminaPrenotazionePerSpettacolo(spettacolo);
			
			em.getTransaction().begin();
			spettacoloDao.delete(spettacolo);
			em.getTransaction().commit();
			return "ok";
		} catch (Exception e) {
			em.getTransaction().rollback();
			return "errore";
		}
		
		
	}
	
	public String modificaSpettacolo (Spettacolo spettacolo,Film film, LocalDateTime dataOra, Sala sala) {
			try {
			
			List<Spettacolo> spettacoli = spettacoloDao.retrieveSortedByDataAndSala(sala);		
			spettacoli.remove(spettacolo);
			
			if(film!=null && sala!= null && dataOra != null) {
				
				LocalDateTime inizioTutti = null;
				LocalDateTime fineTutti = null;
				
				LocalDateTime inizioNuovo = dataOra;
				LocalDateTime fineNuovo = dataOra.plusHours(film.getDurata().getHour()).plusMinutes(film.getDurata().getMinute()).plusMinutes(30L);
				
				if(spettacoli.isEmpty()) {
					em.getTransaction().begin();
					spettacolo.setFilm(film);
					spettacolo.setDataSpettacolo(dataOra);
					spettacolo.setSala(sala);
					
					spettacoloDao.update(spettacolo);
					em.getTransaction().commit();
					return "ok";
				}
					
				for (int i = 0 ; i<spettacoli.size();i++) {					
						 
						fineTutti = spettacoli.get(i).getDataSpettacolo().plusHours(spettacoli.get(i).getFilm().getDurata().getHour()).plusMinutes(spettacoli.get(i).getFilm().getDurata().getMinute()).plusMinutes(30L);
						
						
						if(i==0) {
							inizioTutti = spettacoli.get(i).getDataSpettacolo();
							if(fineNuovo.isBefore(inizioTutti)) {
								
								em.getTransaction().begin();
								spettacolo.setFilm(film);
								spettacolo.setDataSpettacolo(dataOra);
								spettacolo.setSala(sala);
								
								spettacoloDao.update(spettacolo);
								em.getTransaction().commit();
								return "ok";								
							} 
						}
						
						
						if(i== spettacoli.size()-1 ) {
							
							if(spettacoli.size()==1) {
								inizioTutti = spettacoli.get(i).getDataSpettacolo();
								if(inizioNuovo.isAfter(fineTutti)&&fineNuovo.isBefore(inizioTutti)) {
									em.getTransaction().begin();
									spettacolo.setFilm(film);
									spettacolo.setDataSpettacolo(dataOra);
									spettacolo.setSala(sala);
									spettacoloDao.update(spettacolo);
									em.getTransaction().commit();
									return "ok";
								}
							}
							if(inizioNuovo.isAfter(fineTutti)) {
								em.getTransaction().begin();
								spettacolo.setFilm(film);
								spettacolo.setDataSpettacolo(dataOra);
								spettacolo.setSala(sala);
								
								spettacoloDao.update(spettacolo);
								em.getTransaction().commit();
								return "ok";	
							}
						}
						else if(i!=spettacoli.size()-1) {
							inizioTutti = spettacoli.get(i+1).getDataSpettacolo();
						}				
						
						
						
						if(inizioNuovo.isAfter(fineTutti)) {
							if(fineNuovo.isBefore(inizioTutti)) {
								em.getTransaction().begin();
								spettacolo.setFilm(film);
								spettacolo.setDataSpettacolo(dataOra);
								spettacolo.setSala(sala);
								
								spettacoloDao.update(spettacolo);
								em.getTransaction().commit();
								return "ok";								
							} 
						} 
					} 					
					
				
			} else {
				return "deviInserireTuttiICampi";
			}			
			
			
			
			return "sovrapposizione";		
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			em.getTransaction().rollback();
			return "errore";		
			
		}
		
	}
	
	public String eliminaPrenotazione(Prenotazione prenotazione) {
		try {
			if (LocalDateTime.now().isBefore(prenotazione.getSpettacolo().getDataSpettacolo().minusHours(1L))){
			em.getTransaction().begin();
			
			prenotazione.getSpettacolo().getPrenotazioni().remove(prenotazione);
			prenotazione.getUtente().getPrenotazioni().remove(prenotazione);
			prenotazioneDao.delete(prenotazione);
			
			
			em.getTransaction().commit();
			return "ok";
		} else {
			
			
			return "unOraSpettacolo";
		}
		} catch (Exception e) {
			
			e.printStackTrace();
			em.getTransaction().rollback();
			return "errore";
		}		
	}
	
	public String eliminaPrenotazioneAdmin(Prenotazione prenotazione) {
		try {
			
			em.getTransaction().begin();
			inviaMailCancella(prenotazione);
			prenotazione.getSpettacolo().getPrenotazioni().remove(prenotazione);
			prenotazione.getUtente().getPrenotazioni().remove(prenotazione);
			
			prenotazioneDao.delete(prenotazione);
			
			
			em.getTransaction().commit();
			
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return "errore";
		}	
	}
	
	public String eliminaPrenotazionePerData(LocalDate data) {
		try {
			
			em.getTransaction().begin();
			List<Prenotazione> prenotazioniData = prenotazioneDao.findByData(data);
			inviaMailCancellaList(prenotazioniData);
			for (int i = prenotazioniData.size()-1; i>=0;i--) {
				prenotazioniData.get(i).getUtente().getPrenotazioni().remove(prenotazioniData.get(i));
				prenotazioniData.get(i).getSpettacolo().getPrenotazioni().remove(prenotazioniData.get(i));
				prenotazioneDao.delete(prenotazioniData.get(i));
			}
			
			em.getTransaction().commit();
			return "ok";
		} catch(Exception e ) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return "errore";
		}
		
	}
	
	public String eliminaPrenotazionePerSpettacolo (Spettacolo spettacolo) {
		try {
			em.getTransaction().begin();
		
			List<Prenotazione> prenotazioniSpettacolo = prenotazioneDao.findBySpettacolo(spettacolo);
			inviaMailCancellaList(prenotazioniSpettacolo);
			for (int i = prenotazioniSpettacolo.size()-1; i>=0;i--) {
				
				prenotazioniSpettacolo.get(i).getUtente().getPrenotazioni().remove(prenotazioniSpettacolo.get(i));
				prenotazioniSpettacolo.get(i).getSpettacolo().getPrenotazioni().remove(prenotazioniSpettacolo.get(i));
				prenotazioneDao.delete(prenotazioniSpettacolo.get(i));
				}
			
			em.getTransaction().commit();
			
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return "errore";
		}
	}
	
	public void inviaMail(Utente u) {
		
		//pass di utente è SoDeLatina1!
		String to = u.getEmail();
	    String subject = "Accettazione richiesta registrazione";
	    String msg ="Ciao "+u.getUsername()+", la tua richiesta di registrazione è stata accettata dall'admin! \nBenvenuto in CineJANA16!";
	    String from ="cinejana16@gmail.com";
	    String password ="cinejana2020";

	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(from,password);  
	   }  
	   });  

	   //session.setDebug(true);  
	   try {
		   Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(msg, "text/plain");  
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	}
	
public void inviaMail(Utente u, Prenotazione p) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		//pass di utente è SoDeLatina1!
		String to = u.getEmail();
	    String subject = "Conferma Prenotazione";
	    String msg ="Ciao "+u.getUsername()+", hai effettuato con successo la prenotazione!\n"+p.getSpettacolo().getFilm().getTitolo()+" in data "+p.getSpettacolo().getDataSpettacolo().toLocalDate().format(formatter)+" alle "+p.getSpettacolo().getDataSpettacolo().toLocalTime().format(formatter2)+" nella sala "+p.getSpettacolo().getSala().getNome();
	    String from ="cinejana16@gmail.com";
	    String password ="cinejana2020";

	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(from,password);  
	   }  
	   });  

	   //session.setDebug(true);  
	   try {
		   Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(msg, "text/plain");  
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	}

public void inviaMailCancella(Prenotazione p) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		//pass di utente è SoDeLatina1!
		String to = p.getUtente().getEmail();
	    String subject = "Annullamento prenotazione";
	    String msg ="Ciao "+p.getUtente().getUsername()+", ci dispiace informarti che la tua prenotazione relativa a:\n"+p.getSpettacolo().getFilm().getTitolo()+" in data "+p.getSpettacolo().getDataSpettacolo().toLocalDate().format(formatter)+" alle "+p.getSpettacolo().getDataSpettacolo().toLocalTime().format(formatter2)+" nella sala "+p.getSpettacolo().getSala().getNome()+".\nE' stata annullata per dei problemi tecnici, ci scusiamo per il disagio.";
	    String from ="cinejana16@gmail.com";
	    String password ="cinejana2020";

	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(from,password);  
	   }  
	   });  

	   //session.setDebug(true);  
	   try {
		   Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(msg, "text/plain");  
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	}

public void inviaMailCancellaList(List<Prenotazione> p) throws AddressException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
		//pass di utente è SoDeLatina1!
		//InternetAddress[] listaUtentiCinema = new InternetAddress[p.size()];
		String vuota ="";
		for (int i=0; i<p.size();i++) {
			//listaUtentiCinema[i] = InternetAddress.parse(p.get(i).getUtente().getEmail());
			if (i==p.size()-1) {
				vuota+= p.get(i).getUtente().getEmail();
			}else {
			
			vuota+= p.get(i).getUtente().getEmail()+",";
			}
		}
		
		
		
		/*Address[] listaUtentiCinema = new Address[p.size()];
		for (int i=0; i<p.size();i++) {
			listaUtentiCinema[i]=InternetAddress.parse(p.get(i).getUtente().getEmail());
		}*/
	    String subject = "Annullamento prenotazione";
	    String msg ="Ciao, ci dispiace informarti che la tua prenotazione è stata annullata per dei problemi tecnici, ci scusiamo per il disagio.";
	    String from ="cinejana16@gmail.com";
	    String password ="cinejana2020";
	    
	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication(from,password);  
	   }  
	   });  

	   //session.setDebug(true);  
	   try {
		   Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(msg, "text/plain"); 
		    
		   message.addRecipients(Message.RecipientType.BCC, vuota);

		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	}
	
	
}
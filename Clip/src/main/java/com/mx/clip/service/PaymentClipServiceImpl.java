package com.mx.clip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mx.clip.model.PaymentClip;
import com.mx.clip.model.PaymentReporteRespuesta;
import com.mx.clip.repository.IPaymentClipRepository;
import com.mx.clip.response.PaymentReporte;
import com.mx.clip.response.PaymentResponseReporte;
import com.mx.clip.response.PaymentResponseRest;

@Service
public class PaymentClipServiceImpl implements IPaymentClipService {

	private static final Logger log = LoggerFactory.getLogger(PaymentClipServiceImpl.class);

	@Autowired
	private IPaymentClipRepository ipaymentClipRepository;

	@Override
	@Transactional
	public ResponseEntity<PaymentResponseRest> generatePayment(PaymentClip payment) {

		log.info("Inicia metodo generatePayment Clip ");

		PaymentResponseRest response = new PaymentResponseRest();
		Date fecha = new Date();
		List<PaymentClip> list = new ArrayList();

		try {

			payment.setPayment_date(fecha);
			payment.setStatus("NUEVO");
			PaymentClip paymentSave = ipaymentClipRepository.save(payment);

			if (paymentSave != null) {
				list.add(payment);
				response.getPaymentResponse().setPayment(list);

			} else {
				log.error("Error al generar pago Clip");
				response.SetMetadata("Error genera pago", "-1", "Pago Clip no guardado");
				return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			log.error("Error en crear pago Clip");
			response.SetMetadata("Respuesta NO OK", "-1", "Error al crear pago Clip");
			return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.SetMetadata("Respuesta OK", "200", "Pago Clip Generado Exitoso");
		return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaymentResponseRest> listPayments() {
		log.info("Inicia metodo Lista Pagos Clip()");
		PaymentResponseRest response = new PaymentResponseRest();

		try {
			List<PaymentClip> payment = (List<PaymentClip>) ipaymentClipRepository.findAll();
			response.getPaymentResponse().setPayment(payment);

			response.SetMetadata("Respuesta OK", "200", "Respuesta Exitosa pagos Clip");
		} catch (Exception e) {
			response.SetMetadata("Respuesta no valida", "-1", "Respuesta Incorrecta");
			log.error("Error al consultar pagos Clip", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaymentResponseRest> listPaymentsUsers(PaymentClip user) {

		log.info("Inicia metodo Lista Pagos por usuario Clip()");
		PaymentResponseRest response = new PaymentResponseRest();
		List<PaymentClip> list = new ArrayList();

		try {
			List<PaymentClip> payment = (List<PaymentClip>) ipaymentClipRepository.findAll();

			for (Iterator iterator = payment.iterator(); iterator.hasNext();) {
				PaymentClip paymentClip = (PaymentClip) iterator.next();

				if (paymentClip.getUser().equals(user.getUser())) {
					list.add(paymentClip);
					response.getPaymentResponse().setPayment(list);

				}

			}

			response.getPaymentResponse().setPayment(list);

			response.SetMetadata("Respuesta OK", "200", "Respuesta Exitosa pagos Clip");
		} catch (Exception e) {
			response.SetMetadata("Respuesta no valida", "-1", "Respuesta Incorrecta");
			log.error("Error al consultar pagos Clip", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<PaymentResponseRest> aplicaDesenbolso(/* PaymentClip payment, */ Long id) {

		log.info("Inicia metodo Actualiza un pago con desenbolso y estado Procesado ");

		PaymentResponseRest response = new PaymentResponseRest();
		List<PaymentClip> list = new ArrayList<>();

		try {
			Optional<PaymentClip> updatePayment = ipaymentClipRepository.findById(id);

			if (updatePayment.isPresent()) {
				updatePayment.get().setStatus("PROCESADO");

				double interes = (updatePayment.get().getAmount() * 3.5) / 100;
				double desenbolso = (updatePayment.get().getAmount() - interes);
				updatePayment.get().setAmount(desenbolso);

				PaymentClip pagoActualizado = ipaymentClipRepository.save(updatePayment.get());

				if (pagoActualizado != null) {
					response.SetMetadata("Respuesta OK", "200", "Pago Actualizado Correctamente");
					list.add(pagoActualizado);
					response.getPaymentResponse().setPayment(list);
				} else {
					log.error("Error en actualizar el pago");
					response.SetMetadata("Respuesta NO OK", "-1", "Error al actualizar pago");
					return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("EL Numero de pago no existe");
				response.SetMetadata("Respuesta NO OK", "-1", "Error al actaulizar pago");
				return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error("Error en actualizar pago");
			response.SetMetadata("Respuesta NO OK", "-1", "Error al crear pago");
			return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.SetMetadata("Respuesta OK", "200", "Desenbolso aplicado para el Pago");
		return new ResponseEntity<PaymentResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaymentResponseReporte> listPaymentsReporte() {
		log.info("Inicia metodo Lista Pagos Clip()");
		PaymentResponseReporte response = new PaymentResponseReporte();
		List<PaymentReporte> list = new ArrayList();
		PaymentReporte pay = new PaymentReporte();

		PaymentReporteRespuesta payRespuesta = new PaymentReporteRespuesta();
		ArrayList res = new ArrayList();

		List<PaymentReporte> list2 = new ArrayList();

		try {
			List<PaymentClip> payment = (List<PaymentClip>) ipaymentClipRepository.findAll();

			for (Iterator iterator = payment.iterator(); iterator.hasNext();) {
				PaymentClip paymentClip = (PaymentClip) iterator.next();

				if (paymentClip.getUser().equals(paymentClip.getUser())) {

					payRespuesta.setUser(paymentClip.getUser());
					payRespuesta.setSumaPagos(paymentClip.getAmount() + paymentClip.getAmount());

					if (paymentClip.getStatus().equals("NUEVO")) {
						System.out.println("suma nuevos");
						payRespuesta.setPagosNuevos(paymentClip.getAmount() + paymentClip.getAmount());
						// list.add(pay);
					}
					res.add(payRespuesta);

				}

			}
			response.getPaymentReporte().setPayment(res);
			response.SetMetadata("Respuesta OK", "200", "Respuesta Exitosa pagos Clip");
		} catch (Exception e) {
			response.SetMetadata("Respuesta no valida", "-1", "Respuesta Incorrecta");
			log.error("Error al consultar pagos Clip", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<PaymentResponseReporte>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaymentResponseReporte>(response, HttpStatus.OK);
	}

}

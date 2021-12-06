from threading import Thread, Lock, Semaphore
import time, random

# -----------------------------------------------
# Grupo 3 - barbero dormilon
# Integrantes:
# Atalaya Ramirez, Wilker Edison    19200067
# Pasache Lopera, Luiggi Steep      19200092
# Perez Barreto, Javier Alí         19200304
# -----------------------------------------------
mutex = Lock() # mutex

#Intervalo en segundos
customerIntervalMin = 5
customerIntervalMax = 15
haircutDurationMin = 3
haircutDurationMax = 15

class BarberShop:
	waitingCustomers = []
	def __init__(self, barber, numberOfSeats):
		self.barber = barber
		self.numberOfSeats = numberOfSeats
		print('Asientos de la barberia: {0}'.format(numberOfSeats))
		print('Minimo intervalo para que ingrese un cliente: {0}'.format(customerIntervalMin))
		print('Maximo intervalo para que ingrese un cliente: {0}'.format(customerIntervalMax))
		print('Minima duracion de corte de cabello: {0}'.format(haircutDurationMin))
		print('Maxima duracion de corte de cabello: {0}'.format(customerIntervalMax))
		print('---------------------------------------')

	def openShop(self):
		print('Barberia esta abriendo...')
		workingThread = Thread(target = self.barberGoToWork)
		workingThread.start()

	def barberGoToWork(self):
		while True:
			mutex.acquire()

			if len(self.waitingCustomers) > 0:
				c = self.waitingCustomers[0]
				del self.waitingCustomers[0]
				mutex.release()
				self.barber.cutHair(c)
			else:
				mutex.release()
				print('Barbero va a dormir')
				barber.sleep() 
				print('Barbero se despierta')
	
	def enterBarberShop(self, customer):
		mutex.acquire()
		print('>> {0} entró a la barberia y busca un asiento'.format(customer.name))

		if len(self.waitingCustomers) == self.numberOfSeats:
			print('No hay sillas disponibles, {0} se va'.format(customer.name))
			mutex.release()
		else:
			print('{0} se sienta en alguna silla'.format(customer.name))	
			self.waitingCustomers.append(c)	
			mutex.release()
			barber.wakeUp()

class Customer:
	def __init__(self, name):
		self.name = name

class Barber:
	sem = Semaphore()

	def sleep(self):
		self.sem.acquire() # P

	def wakeUp(self):
		self.sem.release() # V

	def cutHair(self, customer):
		print('{0} tiene un corte de cabello'.format(customer.name))

		randomHairCuttingTime = random.randrange(haircutDurationMin, haircutDurationMax+1)
		time.sleep(randomHairCuttingTime)
		print('Terminó el corte de cabello de {0}, se va'.format(customer.name))


if __name__ == '__main__':
	# definicion de clientes
	customers = []
	customers.append(Customer('María')) # encolando clientes
	customers.append(Customer('José'))
	customers.append(Customer('Luis'))
	customers.append(Customer('Juan'))
	customers.append(Customer('Axel'))
	customers.append(Customer('Carlos'))
	customers.append(Customer('Rosa'))
	customers.append(Customer('Iris'))
	customers.append(Customer('Jorge'))
	customers.append(Customer('Víctor'))
	customers.append(Customer('Ana'))
	customers.append(Customer('Andrea'))
	customers.append(Customer('Luz'))
	customers.append(Customer('Carmen'))
	customers.append(Customer('César'))
	customers.append(Customer('Miguel'))
	customers.append(Customer('Tomas'))

	# definicion de barbero
	barber = Barber()

	# definicion de barberia
	barberShop = BarberShop(barber, numberOfSeats=3)
	barberShop.openShop()

	while len(customers) > 0:
		c = customers.pop()	#desencolar cliente
		#Nuevos clientes ingresan a la barberia
		barberShop.enterBarberShop(c) # ingresar a barberia
		customerInterval = random.randrange(customerIntervalMin,customerIntervalMax+1)
		time.sleep(customerInterval)



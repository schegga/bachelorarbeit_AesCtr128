import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Daten einlesen
data = pd.read_excel('benchmark_messdaten_macos.xlsx')

data = data.drop(columns=['Unnamed: 5'])
data.columns = ['Test ID', 'Implementierung', 'Threadanzahl', 'Dateigröße', 'Verschlüsselungszeit']
data.set_index('Test ID', inplace=True)

# ---------- sequential data ----------
data_seq_1KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 1)]
data_seq_10KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 10)]
data_seq_100KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 100)]
data_seq_1000KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 1000)]
data_seq_10000KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 10000)]
data_seq_100000KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 100000)]
data_seq_1000000KB = data[(data['Implementierung'] == 'sequential') & (data['Dateigröße'] == 1000000)]

# sequential data mean
data_seq_1KB_mean = data_seq_1KB['Verschlüsselungszeit'].mean()
data_seq_10KB_mean = data_seq_10KB['Verschlüsselungszeit'].mean()
data_seq_100KB_mean = data_seq_100KB['Verschlüsselungszeit'].mean()
data_seq_1000KB_mean = data_seq_1000KB['Verschlüsselungszeit'].mean()
data_seq_10000KB_mean = data_seq_10000KB['Verschlüsselungszeit'].mean()
data_seq_100000KB_mean = data_seq_100000KB['Verschlüsselungszeit'].mean()
data_seq_1000000KB_mean = data_seq_1000000KB['Verschlüsselungszeit'].mean()

# ---------- javax data ----------
data_javax_1KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 1)]
data_javax_10KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 10)]
data_javax_100KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 100)]
data_javax_1000KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 1000)]
data_javax_10000KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 10000)]
data_javax_100000KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 100000)]
data_javax_1000000KB = data[(data['Implementierung'] == 'javax') & (data['Dateigröße'] == 1000000)]

# javax data mean
data_javax_1KB_mean = data_javax_1KB['Verschlüsselungszeit'].mean()
data_javax_10KB_mean = data_javax_10KB['Verschlüsselungszeit'].mean()
data_javax_100KB_mean = data_javax_100KB['Verschlüsselungszeit'].mean()
data_javax_1000KB_mean = data_javax_1000KB['Verschlüsselungszeit'].mean()
data_javax_10000KB_mean = data_javax_10000KB['Verschlüsselungszeit'].mean()
data_javax_100000KB_mean = data_javax_100000KB['Verschlüsselungszeit'].mean()
data_javax_1000000KB_mean = data_javax_1000000KB['Verschlüsselungszeit'].mean()

# ---------- parallel data ----------
# 1 Thread
data_par_1KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 1)]
data_par_10KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 1)]
data_par_100KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 1)]
data_par_1000KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 1)]
data_par_10000KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 1)]
data_par_100000KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 1)]
data_par_1000000KB_1T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 1)]

# 1 Thread mean
data_par_1KB_1T_mean = data_par_1KB_1T['Verschlüsselungszeit'].mean()
data_par_10KB_1T_mean = data_par_10KB_1T['Verschlüsselungszeit'].mean()
data_par_100KB_1T_mean = data_par_100KB_1T['Verschlüsselungszeit'].mean()
data_par_1000KB_1T_mean = data_par_1000KB_1T['Verschlüsselungszeit'].mean()
data_par_10000KB_1T_mean = data_par_10000KB_1T['Verschlüsselungszeit'].mean()
data_par_100000KB_1T_mean = data_par_100000KB_1T['Verschlüsselungszeit'].mean()
data_par_1000000KB_1T_mean = data_par_1000000KB_1T['Verschlüsselungszeit'].mean()

# 2 Threads
data_par_1KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 2)]
data_par_10KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 2)]
data_par_100KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 2)]
data_par_1000KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 2)]
data_par_10000KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 2)]
data_par_100000KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 2)]
data_par_1000000KB_2T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 2)]

# 2 Threads mean
data_par_1KB_2T_mean = data_par_1KB_2T['Verschlüsselungszeit'].mean()
data_par_10KB_2T_mean = data_par_10KB_2T['Verschlüsselungszeit'].mean()
data_par_100KB_2T_mean = data_par_100KB_2T['Verschlüsselungszeit'].mean()
data_par_1000KB_2T_mean = data_par_1000KB_2T['Verschlüsselungszeit'].mean()
data_par_10000KB_2T_mean = data_par_10000KB_2T['Verschlüsselungszeit'].mean()
data_par_100000KB_2T_mean = data_par_100000KB_2T['Verschlüsselungszeit'].mean()
data_par_1000000KB_2T_mean = data_par_1000000KB_2T['Verschlüsselungszeit'].mean()

# 4 Threads
data_par_1KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 4)]
data_par_10KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 4)]
data_par_100KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 4)]
data_par_1000KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 4)]
data_par_10000KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 4)]
data_par_100000KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 4)]
data_par_1000000KB_4T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 4)]

# 4 Threads mean
data_par_1KB_4T_mean = data_par_1KB_4T['Verschlüsselungszeit'].mean()
data_par_10KB_4T_mean = data_par_10KB_4T['Verschlüsselungszeit'].mean()
data_par_100KB_4T_mean = data_par_100KB_4T['Verschlüsselungszeit'].mean()
data_par_1000KB_4T_mean = data_par_1000KB_4T['Verschlüsselungszeit'].mean()
data_par_10000KB_4T_mean = data_par_10000KB_4T['Verschlüsselungszeit'].mean()
data_par_100000KB_4T_mean = data_par_100000KB_4T['Verschlüsselungszeit'].mean()
data_par_1000000KB_4T_mean = data_par_1000000KB_4T['Verschlüsselungszeit'].mean()

# 8 Threads
data_par_1KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 8)]
data_par_10KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 8)]
data_par_100KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 8)]
data_par_1000KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 8)]
data_par_10000KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 8)]
data_par_100000KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 8)]
data_par_1000000KB_8T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 8)]

# 8 Threads mean
data_par_1KB_8T_mean = data_par_1KB_8T['Verschlüsselungszeit'].mean()
data_par_10KB_8T_mean = data_par_10KB_8T['Verschlüsselungszeit'].mean()
data_par_100KB_8T_mean = data_par_100KB_8T['Verschlüsselungszeit'].mean()
data_par_1000KB_8T_mean = data_par_1000KB_8T['Verschlüsselungszeit'].mean()
data_par_10000KB_8T_mean = data_par_10000KB_8T['Verschlüsselungszeit'].mean()
data_par_100000KB_8T_mean = data_par_100000KB_8T['Verschlüsselungszeit'].mean()
data_par_1000000KB_8T_mean = data_par_1000000KB_8T['Verschlüsselungszeit'].mean()

# 16 Threads
data_par_1KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 16)]
data_par_10KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 16)]
data_par_100KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 16)]
data_par_1000KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 16)]
data_par_10000KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 16)]
data_par_100000KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 16)]
data_par_1000000KB_16T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 16)]

# 16 Threads mean
data_par_1KB_16T_mean = data_par_1KB_16T['Verschlüsselungszeit'].mean()
data_par_10KB_16T_mean = data_par_10KB_16T['Verschlüsselungszeit'].mean()
data_par_100KB_16T_mean = data_par_100KB_16T['Verschlüsselungszeit'].mean()
data_par_1000KB_16T_mean = data_par_1000KB_16T['Verschlüsselungszeit'].mean()
data_par_10000KB_16T_mean = data_par_10000KB_16T['Verschlüsselungszeit'].mean()
data_par_100000KB_16T_mean = data_par_100000KB_16T['Verschlüsselungszeit'].mean()
data_par_1000000KB_16T_mean = data_par_1000000KB_16T['Verschlüsselungszeit'].mean()

# 32 Threads
data_par_1KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 32)]
data_par_10KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 32)]
data_par_100KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 32)]
data_par_1000KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 32)]
data_par_10000KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 32)]
data_par_100000KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 32)]
data_par_1000000KB_32T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 32)]

# 32 Threads mean
data_par_1KB_32T_mean = data_par_1KB_32T['Verschlüsselungszeit'].mean()
data_par_10KB_32T_mean = data_par_10KB_32T['Verschlüsselungszeit'].mean()
data_par_100KB_32T_mean = data_par_100KB_32T['Verschlüsselungszeit'].mean()
data_par_1000KB_32T_mean = data_par_1000KB_32T['Verschlüsselungszeit'].mean()
data_par_10000KB_32T_mean = data_par_10000KB_32T['Verschlüsselungszeit'].mean()
data_par_100000KB_32T_mean = data_par_100000KB_32T['Verschlüsselungszeit'].mean()
data_par_1000000KB_32T_mean = data_par_1000000KB_32T['Verschlüsselungszeit'].mean()

# 64 Threads
data_par_1KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 64)]
data_par_10KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 64)]
data_par_100KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 64)]
data_par_1000KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 64)]
data_par_10000KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 64)]
data_par_100000KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 64)]
data_par_1000000KB_64T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 64)]

# 64 Threads mean
data_par_1KB_64T_mean = data_par_1KB_64T['Verschlüsselungszeit'].mean()
data_par_10KB_64T_mean = data_par_10KB_64T['Verschlüsselungszeit'].mean()
data_par_100KB_64T_mean = data_par_100KB_64T['Verschlüsselungszeit'].mean()
data_par_1000KB_64T_mean = data_par_1000KB_64T['Verschlüsselungszeit'].mean()
data_par_10000KB_64T_mean = data_par_10000KB_64T['Verschlüsselungszeit'].mean()
data_par_100000KB_64T_mean = data_par_100000KB_64T['Verschlüsselungszeit'].mean()
data_par_1000000KB_64T_mean = data_par_1000000KB_64T['Verschlüsselungszeit'].mean()

# 128 Threads
data_par_1KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1) & (data['Threadanzahl'] == 128)]
data_par_10KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10) & (data['Threadanzahl'] == 128)]
data_par_100KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100) & (data['Threadanzahl'] == 128)]
data_par_1000KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000) & (data['Threadanzahl'] == 128)]
data_par_10000KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 10000) & (data['Threadanzahl'] == 128)]
data_par_100000KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 100000) & (data['Threadanzahl'] == 128)]
data_par_1000000KB_128T = data[(data['Implementierung'] == 'parallel') & (data['Dateigröße'] == 1000000) & (data['Threadanzahl'] == 128)]

# 128 Threads mean
data_par_1KB_128T_mean = data_par_1KB_128T['Verschlüsselungszeit'].mean()
data_par_10KB_128T_mean = data_par_10KB_128T['Verschlüsselungszeit'].mean()
data_par_100KB_128T_mean = data_par_100KB_128T['Verschlüsselungszeit'].mean()
data_par_1000KB_128T_mean = data_par_1000KB_128T['Verschlüsselungszeit'].mean()
data_par_10000KB_128T_mean = data_par_10000KB_128T['Verschlüsselungszeit'].mean()
data_par_100000KB_128T_mean = data_par_100000KB_128T['Verschlüsselungszeit'].mean()
data_par_1000000KB_128T_mean = data_par_1000000KB_128T['Verschlüsselungszeit'].mean()

# ---------- Graphs ----------
fontsize_title = 20
fontsize_label = 16
fontsize_ticks = 16
# ---------- overall vergleich ----------
x_axis = [1, 10, 100, 1000, 10000, 100000, 1000000]

encryption_times_seq = [data_seq_1KB_mean, data_seq_10KB_mean, data_seq_100KB_mean, data_seq_1000KB_mean, 
                        data_seq_10000KB_mean, data_seq_100000KB_mean, data_seq_1000000KB_mean]

encryption_times_javax = [data_javax_1KB_mean, data_javax_10KB_mean, data_javax_100KB_mean, data_javax_1000KB_mean, 
                          data_javax_10000KB_mean, data_javax_100000KB_mean, data_javax_1000000KB_mean]

encryption_times_par_1T = [data_par_1KB_1T_mean, data_par_10KB_1T_mean, data_par_100KB_1T_mean, data_par_1000KB_1T_mean,
                            data_par_10000KB_1T_mean, data_par_100000KB_1T_mean, data_par_1000000KB_1T_mean]
encryption_times_par_2T = [data_par_1KB_2T_mean, data_par_10KB_2T_mean, data_par_100KB_2T_mean, data_par_1000KB_2T_mean,
                            data_par_10000KB_2T_mean, data_par_100000KB_2T_mean, data_par_1000000KB_2T_mean]
encryption_times_par_4T = [data_par_1KB_4T_mean, data_par_10KB_4T_mean, data_par_100KB_4T_mean, data_par_1000KB_4T_mean,
                            data_par_10000KB_4T_mean, data_par_100000KB_4T_mean, data_par_1000000KB_4T_mean]
encryption_times_par_8T = [data_par_1KB_8T_mean, data_par_10KB_8T_mean, data_par_100KB_8T_mean, data_par_1000KB_8T_mean,
                            data_par_10000KB_8T_mean, data_par_100000KB_8T_mean, data_par_1000000KB_8T_mean]
encryption_times_par_16T = [data_par_1KB_16T_mean, data_par_10KB_16T_mean, data_par_100KB_16T_mean, data_par_1000KB_16T_mean,
                            data_par_10000KB_16T_mean, data_par_100000KB_16T_mean, data_par_1000000KB_16T_mean]
encryption_times_par_32T = [data_par_1KB_32T_mean, data_par_10KB_32T_mean, data_par_100KB_32T_mean, data_par_1000KB_32T_mean,
                            data_par_10000KB_32T_mean, data_par_100000KB_32T_mean, data_par_1000000KB_32T_mean]
encryption_times_par_64T = [data_par_1KB_64T_mean, data_par_10KB_64T_mean, data_par_100KB_64T_mean, data_par_1000KB_64T_mean,
                            data_par_10000KB_64T_mean, data_par_100000KB_64T_mean, data_par_1000000KB_64T_mean]
encryption_times_par_128T = [data_par_1KB_128T_mean, data_par_10KB_128T_mean, data_par_100KB_128T_mean, data_par_1000KB_128T_mean,
                            data_par_10000KB_128T_mean, data_par_100000KB_128T_mean, data_par_1000000KB_128T_mean]

encryption_times_par = [
    encryption_times_par_1T, encryption_times_par_2T, encryption_times_par_4T, 
    encryption_times_par_8T, encryption_times_par_16T, encryption_times_par_32T, 
    encryption_times_par_64T, encryption_times_par_128T
]

# Liste der Thread-Labels
thread_labels = ['1T', '2T', '4T', '8T', '16T', '32T', '64T', '128T']

# Erstelle einen neuen Plot für die parallelen Daten
plt.figure(figsize=(10, 10))

# Füge jeden Datensatz zum Plot hinzu
plt.plot(x_axis, encryption_times_seq, marker='o', label='sequential', color='blue')


grey_values = np.linspace(0.8, 0, len(thread_labels))
for i, encryption_times in enumerate(encryption_times_par):
    
    plt.plot(x_axis, encryption_times, marker='o', label=thread_labels[i], color=str(grey_values[i]))

plt.plot(x_axis, encryption_times_javax, marker='o', label='javax', color='red')

# Setze die x-Achse auf logarithmische Skala
plt.xscale('log')
plt.yscale('log')

# Achsentitel und Legende hinzu
plt.title('CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Dateigröße [KB]', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.legend()
plt.grid(True)

# Graph als svg speichern
path_to_save = 'graphen/macos/0_overall_vergleich_logarithmisch.svg'
plt.savefig(path_to_save)

# ---------- parallel vergeleich ----------

# Erstelle einen neuen Plot für die parallelen Daten
plt.figure(figsize=(11, 10))

# Füge jeden Datensatz zum Plot hinzu

grey_values = np.linspace(0.8, 0, len(thread_labels))
for i, encryption_times in enumerate(encryption_times_par):
    
    plt.plot(x_axis, encryption_times, marker='o', label=thread_labels[i], color=str(grey_values[i]))

# Setze die x-Achse auf logarithmische Skala
plt.xscale('log')
plt.yscale('log')

# Achsentitel und Legende hinzu
plt.title('CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Dateigröße [KB]', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.legend()
plt.grid(True)

# Graph als svg speichern
path_to_save = 'graphen/macos/1_parallel_vergleich_logarithmisch.svg'
plt.savefig(path_to_save)

# ---------- 1KB vergleich ----------
# Daten für 1KB
encryption_times_1KB = [
    data_javax_1KB_mean,
    data_seq_1KB_mean,  
    data_par_1KB_1T_mean, data_par_1KB_2T_mean, data_par_1KB_4T_mean, 
    data_par_1KB_8T_mean, data_par_1KB_16T_mean, data_par_1KB_32T_mean, 
    data_par_1KB_64T_mean, data_par_1KB_128T_mean
]

labels = ['javax', 'sequential', '1T', '2T', '4T', '8T', '16T', '32T', '64T', '128T']
colors = ['red' , 'blue'] + [str(grey) for grey in grey_values]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_1KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_1KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 1 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/1KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 10KB vergleich ----------
# Daten für 10KB
encryption_times_10KB = [
    data_javax_10KB_mean, 
    data_seq_10KB_mean,
    data_par_10KB_1T_mean, data_par_10KB_2T_mean, data_par_10KB_4T_mean, 
    data_par_10KB_8T_mean, data_par_10KB_16T_mean, data_par_10KB_32T_mean, 
    data_par_10KB_64T_mean, data_par_10KB_128T_mean
]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_10KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_10KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 10 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/10KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 100KB vergleich ----------
# Daten für 100KB
encryption_times_100KB = [
    data_javax_100KB_mean,
    data_seq_100KB_mean,  
    data_par_100KB_1T_mean, data_par_100KB_2T_mean, data_par_100KB_4T_mean, 
    data_par_100KB_8T_mean, data_par_100KB_16T_mean, data_par_100KB_32T_mean, 
    data_par_100KB_64T_mean, data_par_100KB_128T_mean
]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_100KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_100KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 100 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/100KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 1000KB vergleich ----------
# Daten für 1000KB
encryption_times_1000KB = [
    data_javax_1000KB_mean, 
    data_seq_1000KB_mean, 
    data_par_1000KB_1T_mean, data_par_1000KB_2T_mean, data_par_1000KB_4T_mean, 
    data_par_1000KB_8T_mean, data_par_1000KB_16T_mean, data_par_1000KB_32T_mean, 
    data_par_1000KB_64T_mean, data_par_1000KB_128T_mean
]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_1000KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_1000KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 1.000 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/1000KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 10000KB vergleich ----------
# Daten für 10000KB
encryption_times_10000KB = [
    data_javax_10000KB_mean, 
    data_seq_10000KB_mean, 
    data_par_10000KB_1T_mean, data_par_10000KB_2T_mean, data_par_10000KB_4T_mean, 
    data_par_10000KB_8T_mean, data_par_10000KB_16T_mean, data_par_10000KB_32T_mean, 
    data_par_10000KB_64T_mean, data_par_10000KB_128T_mean
]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_10000KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_10000KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 10.000 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/10000KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 100000KB vergleich ----------
# Daten für 100000KB
encryption_times_100000KB = [
    data_javax_100000KB_mean, 
    data_seq_100000KB_mean, 
    data_par_100000KB_1T_mean, data_par_100000KB_2T_mean, data_par_100000KB_4T_mean, 
    data_par_100000KB_8T_mean, data_par_100000KB_16T_mean, data_par_100000KB_32T_mean, 
    data_par_100000KB_64T_mean, data_par_100000KB_128T_mean
]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_100000KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_100000KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 100.000 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/100000KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 1000000KB vergleich ----------
# Daten für 1000000KB
encryption_times_1000000KB = [
    data_javax_1000000KB_mean,
    data_seq_1000000KB_mean,  
    data_par_1000000KB_1T_mean, data_par_1000000KB_2T_mean, data_par_1000000KB_4T_mean, 
    data_par_1000000KB_8T_mean, data_par_1000000KB_16T_mean, data_par_1000000KB_32T_mean, 
    data_par_1000000KB_64T_mean, data_par_1000000KB_128T_mean
]

# Erstellen Sie einen neuen Plot für das Balkendiagramm
plt.figure(figsize=(12, 8))

# Balkendiagramm erstellen
bars = plt.bar(labels, encryption_times_1000000KB, color=colors)

# Hinzufügen von exakten Mittelwerten zu den Balken
for i, bar in enumerate(bars):
    yval = bar.get_height() + 0.1
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(encryption_times_1000000KB[i], 2), va='bottom', ha='center')

# Achsentitel und Legende hinzufügen
plt.title('Verschlüsselungszeiten 1.000.000 KB - CPU: Apple M1 Pro', fontsize=fontsize_title)
plt.xlabel('Implementierung / Anzahl der Threads', fontsize=fontsize_label)
plt.ylabel('Verschlüsselungszeit [ms]', fontsize=fontsize_label)
plt.xticks(fontsize=fontsize_ticks)
plt.yticks(fontsize=fontsize_ticks)
plt.tight_layout()
plt.grid(axis='y')

# Diagramm als svg speichern
path_to_save_bar = 'graphen/macos/1000000KB_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# ---------- 7 graphen/macos nebeneinander vergleich ----------
# Erstellen Sie ein Figure-Objekt mit 4 Zeilen und 2 Spalten
fig, axes = plt.subplots(nrows=4, ncols=2, figsize=(16, 24))

# Eine Liste der Verschlüsselungszeiten für einfacheren Zugriff
all_encryption_times = [
    encryption_times_1KB, encryption_times_10KB, 
    encryption_times_100KB, encryption_times_1000KB,
    encryption_times_10000KB, encryption_times_100000KB,
    encryption_times_1000000KB
]

# Titel für die Diagramme (gekürzt)
titles = [
    'Verschlüsselungszeiten [ms] für\nDateigröße: 1 KB', 'Verschlüsselungszeiten [ms] für\nDateigröße: 10 KB', 'Verschlüsselungszeiten [ms] für\nDateigröße: 100 KB', 'Verschlüsselungszeiten [ms] für\nDateigröße: 1.000 KB',
    'Verschlüsselungszeiten [ms] für\nDateigröße: 10.000 KB', 'Verschlüsselungszeiten [ms] für\nDateigröße: 100.000 KB', 'Verschlüsselungszeiten [ms] für\nDateigröße: 1.000.000 KB'
]

labels = ['javax', 'seq', '1T', '2T', '4T', '8T', '16T', '32T', '64T', '128T']

# Loop durch alle Verschlüsselungszeiten und Achsen
for index, (ax, times, title) in enumerate(zip(axes.flatten(), all_encryption_times, titles)):
    bars = ax.bar(labels, times, color=colors)
    for i, bar in enumerate(bars):
        yval = bar.get_height() + 0.1
        ax.text(bar.get_x() + bar.get_width()/2.0, yval + 0.005, round(times[i], 2), va='bottom', ha='center', fontsize=10)  # Verkleinerte Schriftgröße für bessere Darstellung
    
    # Y- und X-Achsenbeschriftungen entfernen
    ax.set_xlabel('')
    ax.set_ylabel('')
    ax.tick_params(axis='both', which='major', labelsize=16)
    ax.grid(axis='y')
    
    # Textposition festlegen; für die letzten drei Diagramme nach rechts verschieben
    text_position = 0.5
    if index in [4, 5, 6]:
        text_position = 0.65
    
    # Titel innerhalb des Diagramms setzen, horizontal entsprechend text_position, vertikal knapp unter dem oberen Rand
    ax.text(text_position, 0.92, title, transform=ax.transAxes, fontsize=17, ha="center", va="center", bbox=dict(facecolor='white', alpha=1, edgecolor='black'))

# Letztes Diagramm "unsichtbar" machen
axes[-1, -1].axis('off')  # Schaltet Achsen und Ticks aus

plt.tight_layout()

# Speichern der Diagramme als eine SVG-Datei
path_to_save_bar = 'graphen/macos/combined_balkendiagramm.svg'
plt.savefig(path_to_save_bar)

# Diagramm anzeigen
plt.show()

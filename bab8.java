import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;



abstract class MataKuliah {
    protected String nama;
    protected double tugas, kuis, uts, uas;

    public MataKuliah(String nama) {
        this.nama = nama;
    }

    public void setNilai(double tugas, double kuis, double uts, double uas) {
        this.tugas = tugas;
        this.kuis   = kuis;
        this.uts    = uts;
        this.uas    = uas;
    }

    public String getNama() { return nama; }

    public abstract double bab8();
}

class Pemlan extends MataKuliah {
    public Pemlan() { super("Pemlan"); }

    @Override
    public double bab8() {
        return (tugas * 0.20) + (kuis * 0.20) + (uts * 0.25) + (uas * 0.35);
    }
}

class ASD extends MataKuliah {
    public ASD() { super("ASD"); }

    @Override
    public double bab8() {
        return (tugas * 0.15) + (kuis * 0.25) + (uts * 0.30) + (uas * 0.30);
    }
}

class Matkomlan extends MataKuliah {
    public Matkomlan() { super("Matkomlan"); }

    @Override
    public double bab8() {
        return (tugas * 0.25) + (kuis * 0.15) + (uts * 0.30) + (uas * 0.30);
    }
}

class Probstat extends MataKuliah {
    public Probstat() { super("Probstat"); }

    @Override
    public double bab8() {
        return (tugas * 0.10) + (kuis * 0.20) + (uts * 0.30) + (uas * 0.40);
    }
}



public class bab8 extends JFrame {

    private JRadioButton rbASD, rbPemlan, rbMatkomlan, rbProbstat;
    private ButtonGroup bg;

    private JTextField tfTugas, tfKuis, tfUTS, tfUAS, tfHasil;

    private JTextArea taHasil;

    private JButton btnHitung, btnTampilkan;

    private MataKuliah[] matkul = {new Pemlan(), new ASD(), new Matkomlan(), new Probstat()};

    private HashMap<String, Double> nilaiAkhirMap = new HashMap<>();

    public bab8() {
        setTitle("Hitung Nilai Akhir dengan GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        layoutComponents();

        rbPemlan.setSelected(true);
    }

    private void initComponents() {
        rbASD       = new JRadioButton("ASD");
        rbPemlan    = new JRadioButton("Pemlan");
        rbMatkomlan = new JRadioButton("Matkomlan");
        rbProbstat  = new JRadioButton("Probstat");

        bg = new ButtonGroup();
        bg.add(rbASD);
        bg.add(rbPemlan);
        bg.add(rbMatkomlan);
        bg.add(rbProbstat);

        ActionListener radioListener = e -> kosongkanField();
        rbASD.addActionListener(radioListener);
        rbPemlan.addActionListener(radioListener);
        rbMatkomlan.addActionListener(radioListener);
        rbProbstat.addActionListener(radioListener);

        tfTugas = new JTextField(10);
        tfKuis  = new JTextField(10);
        tfUTS   = new JTextField(10);
        tfUAS   = new JTextField(10);
        tfHasil = new JTextField(10);
        tfHasil.setEditable(false);
        tfHasil.setBackground(new Color(230, 230, 230));

        btnHitung     = new JButton("Hitung");
        btnTampilkan  = new JButton("Tampilkan nilai semua matkul");

        taHasil = new JTextArea(8, 30);
        taHasil.setEditable(false);
        taHasil.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taHasil.setBorder(BorderFactory.createTitledBorder("HASIL NILAI SEMUA MATA KULIAH"));

        btnHitung.addActionListener(e -> hitungNilai());

        btnTampilkan.addActionListener(e -> tampilkanSemuaNilai());
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        JLabel lblJudul = new JLabel("Hitung Nilai Akhir", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        lblJudul.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        add(lblJudul, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 5, 15));

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        radioPanel.add(rbASD);
        radioPanel.add(rbPemlan);
        radioPanel.add(rbMatkomlan);
        radioPanel.add(rbProbstat);
        centerPanel.add(radioPanel);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 5, 4, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Tugas :", "Kuis :", "UTS :", "UAS :", "Hasil :"};
        JTextField[] fields = {tfTugas, tfKuis, tfUTS, tfUAS, tfHasil};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        gbc.gridx = 0; gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnHitung, gbc);

        centerPanel.add(formPanel);

        JScrollPane scrollPane = new JScrollPane(taHasil);
        scrollPane.setPreferredSize(new Dimension(380, 160));
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        bottomPanel.add(btnTampilkan);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void kosongkanField() {
        tfTugas.setText("");
        tfKuis.setText("");
        tfUTS.setText("");
        tfUAS.setText("");
        tfHasil.setText("");
    }

    private MataKuliah getMatkulDipilih() {
        if (rbASD.isSelected())       return matkul[1];
        if (rbPemlan.isSelected())    return matkul[0];
        if (rbMatkomlan.isSelected()) return matkul[2];
        if (rbProbstat.isSelected())  return matkul[3];
        return null;
    }

    private void hitungNilai() {
        try {
            double tugas = Double.parseDouble(tfTugas.getText().trim());
            double kuis  = Double.parseDouble(tfKuis.getText().trim());
            double uts   = Double.parseDouble(tfUTS.getText().trim());
            double uas   = Double.parseDouble(tfUAS.getText().trim());

            if (tugas < 0 || tugas > 100 || kuis < 0 || kuis > 100 ||
                uts < 0   || uts > 100   || uas  < 0 || uas  > 100) {
                JOptionPane.showMessageDialog(this,
                    "Nilai harus antara 0 - 100!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MataKuliah mk = getMatkulDipilih();
            if (mk == null) {
                JOptionPane.showMessageDialog(this,
                    "Pilih mata kuliah terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            mk.setNilai(tugas, kuis, uts, uas);
            double hasil = mk.bab8();

            tfHasil.setText(String.format("%.1f", hasil));

            nilaiAkhirMap.put(mk.getNama(), hasil);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanSemuaNilai() {
        if (nilaiAkhirMap.isEmpty()) {
            taHasil.setText("Belum ada nilai yang dihitung.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] urutan = {"Pemlan", "ASD", "Matkomlan", "Probstat"};
        for (String nama : urutan) {
            if (nilaiAkhirMap.containsKey(nama)) {
                sb.append(String.format("%-12s: %.1f%n", nama, nilaiAkhirMap.get(nama)));
            }
        }
        taHasil.setText(sb.toString());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new bab8().setVisible(true);
        });
    }
}
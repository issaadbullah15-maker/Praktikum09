import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class bab9 extends JFrame {

    private JTextField tfNama, tfTanggal, tfNoPendaftaran, tfNoTelp, tfEmail;
    private JTextArea  taAlamat;
    private JButton    btnSubmit;

    private static final Color PRIMARY     = new Color(41, 128, 185);
    private static final Color PRIMARY_DRK = new Color(21, 100, 160);
    private static final Color BG          = new Color(236, 240, 245);
    private static final Color CARD_BG     = Color.WHITE;
    private static final Color LABEL_CLR   = new Color(60, 60, 80);
    private static final Color BORDER_CLR  = new Color(180, 195, 215);
    private static final Color ACCENT      = new Color(39, 174, 96);

    public bab9() {
        setTitle("Formulir Daftar Ulang Mahasiswa Baru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY);
        header.setPreferredSize(new Dimension(520, 90));
        header.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel lblUniv = new JLabel("UNIVERSITAS NUSANTARA");
        lblUniv.setFont(new Font("Arial", Font.BOLD, 18));
        lblUniv.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Formulir Daftar Ulang Mahasiswa Baru");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(new Color(200, 225, 255));

        JPanel headerText = new JPanel(new GridLayout(2, 1, 0, 3));
        headerText.setOpaque(false);
        headerText.add(lblUniv);
        headerText.add(lblSub);
        header.add(headerText, BorderLayout.CENTER);

        JLabel lblIcon = new JLabel("🎓", SwingConstants.RIGHT);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        header.add(lblIcon, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 30, 10, 30),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_CLR, 1, true),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
            )
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(7, 5, 7, 5);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.anchor  = GridBagConstraints.WEST;

        tfNama          = createTextField();
        tfTanggal       = createTextField();
        tfNoPendaftaran = createTextField();
        tfNoTelp        = createTextField();
        tfEmail         = createTextField();
        taAlamat        = new JTextArea(3, 20);
        taAlamat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        taAlamat.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_CLR),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        taAlamat.setLineWrap(true);
        taAlamat.setWrapStyleWord(true);

        Object[][] rows = {
            {"Nama Lengkap",      tfNama},
            {"Tanggal Lahir",     tfTanggal},
            {"Nomor Pendaftaran", tfNoPendaftaran},
            {"No. Telp",          tfNoTelp},
            {"Alamat",            taAlamat},
            {"E-mail",            tfEmail},
        };

        for (int i = 0; i < rows.length; i++) {
            JLabel lbl = new JLabel((String) rows[i][0]);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lbl.setForeground(LABEL_CLR);
            lbl.setPreferredSize(new Dimension(140, 30));

            gbc.gridx = 0; gbc.gridy = i; gbc.weightx = 0;
            card.add(lbl, gbc);

            gbc.gridx = 1; gbc.weightx = 1;
            if (rows[i][1] instanceof JTextArea) {
                JScrollPane sp = new JScrollPane((JTextArea) rows[i][1]);
                sp.setBorder(BorderFactory.createLineBorder(BORDER_CLR));
                card.add(sp, gbc);
            } else {
                card.add((Component) rows[i][1], gbc);
            }
        }

        gbc.gridx = 0; gbc.gridy = rows.length; gbc.gridwidth = 2; gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 5, 5);
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_CLR);
        card.add(sep, gbc);

        btnSubmit = new JButton("  Submit  ▶");
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSubmit.setBackground(PRIMARY);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmit.setOpaque(true);

        btnSubmit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e)  { btnSubmit.setBackground(PRIMARY_DRK); }
            public void mouseExited(MouseEvent e)   { btnSubmit.setBackground(PRIMARY); }
        });

        btnSubmit.addActionListener(e -> handleSubmit());

        gbc.gridx = 0; gbc.gridy = rows.length + 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST; gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        card.add(btnSubmit, gbc);

        JScrollPane scrollCard = new JScrollPane(card);
        scrollCard.setBorder(null);
        scrollCard.setBackground(BG);
        scrollCard.getViewport().setBackground(BG);

        add(scrollCard, BorderLayout.CENTER);

        JLabel footer = new JLabel("© 2024 Universitas Nusantara – Sistem Pendaftaran Online", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        footer.setForeground(new Color(140, 140, 160));
        footer.setBorder(BorderFactory.createEmptyBorder(6, 0, 8, 0));
        footer.setBackground(BG);
        footer.setOpaque(true);
        add(footer, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_CLR),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        tf.setPreferredSize(new Dimension(280, 33));
        return tf;
    }

    private void handleSubmit() {
        String nama    = tfNama.getText().trim();
        String tgl     = tfTanggal.getText().trim();
        String noPend  = tfNoPendaftaran.getText().trim();
        String noTelp  = tfNoTelp.getText().trim();
        String alamat  = taAlamat.getText().trim();
        String email   = tfEmail.getText().trim();

        if (nama.isEmpty() || tgl.isEmpty() || noPend.isEmpty() ||
            noTelp.isEmpty() || alamat.isEmpty() || email.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "⚠  Semua kolom harus diisi!\nSilakan lengkapi data sebelum submit.",
                "Data Belum Lengkap",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int pilihan = JOptionPane.showConfirmDialog(
            this,
            "Apakah anda yakin data yang Anda isi sudah benar?",
            "Konfirmasi Data",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (pilihan == JOptionPane.OK_OPTION) {
            tampilkanData(nama, tgl, noPend, noTelp, alamat, email);
        }
    }

    private void tampilkanData(String nama, String tgl, String noPend,
                                String noTelp, String alamat, String email) {
        JDialog dialog = new JDialog(this, "Data Mahasiswa", true);
        dialog.setSize(430, 370);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(BG);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel dHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dHeader.setBackground(ACCENT);
        dHeader.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        JLabel dTitle = new JLabel("✔  Data Mahasiswa Berhasil Disimpan");
        dTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dTitle.setForeground(Color.WHITE);
        dHeader.add(dTitle);
        dialog.add(dHeader, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridBagLayout());
        dataPanel.setBackground(CARD_BG);
        dataPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(15, 20, 15, 20),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_CLR, 1, true),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
            )
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets  = new Insets(5, 5, 5, 10);
        g.anchor  = GridBagConstraints.WEST;

        String[][] data = {
            {"Nama",            nama},
            {"Tanggal Lahir",   tgl},
            {"No. Pendaftaran", noPend},
            {"No. Telp",        noTelp},
            {"Alamat",          alamat},
            {"E-mail",          email},
        };

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 13);

        for (int i = 0; i < data.length; i++) {
            g.gridx = 0; g.gridy = i; g.weightx = 0;
            JLabel lKey = new JLabel(data[i][0]);
            lKey.setFont(labelFont);
            lKey.setForeground(new Color(80, 80, 110));
            lKey.setPreferredSize(new Dimension(130, 25));
            dataPanel.add(lKey, g);

            g.gridx = 1; g.weightx = 0;
            JLabel lColon = new JLabel(":");
            lColon.setFont(labelFont);
            lColon.setForeground(new Color(80, 80, 110));
            dataPanel.add(lColon, g);

            g.gridx = 2; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL;
            JLabel lVal = new JLabel(data[i][1]);
            lVal.setFont(valueFont);
            lVal.setForeground(LABEL_CLR);
            dataPanel.add(lVal, g);
        }

        dialog.add(dataPanel, BorderLayout.CENTER);

        JButton btnTutup = new JButton("Tutup");
        btnTutup.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTutup.setBackground(PRIMARY);
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setFocusPainted(false);
        btnTutup.setOpaque(true);
        btnTutup.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));
        btnTutup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTutup.addActionListener(e -> {
            dialog.dispose();
            resetForm();
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(BG);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        btnPanel.add(btnTutup);
        dialog.add(btnPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void resetForm() {
        tfNama.setText("");
        tfTanggal.setText("");
        tfNoPendaftaran.setText("");
        tfNoTelp.setText("");
        taAlamat.setText("");
        tfEmail.setText("");
        tfNama.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new bab9().setVisible(true);
        });
    }
}
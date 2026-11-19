import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KpopPlaylistGUI extends JFrame implements ActionListener {

    private JTextField judulField;
    private JComboBox<String> artisCombo;
    private JButton tambahButton;
    private JButton hapusButton;
    private JButton resetButton;
    private JList<String> playlistList;
    private DefaultListModel<String> playlistModel;
    private JLabel totalLabel;

    private final Color WARNA_UTAMA = new Color(142, 68, 173);
    private final Color WARNA_AKSEN = new Color(233, 30, 99);
    private final Color WARNA_LATAR = new Color(255, 245, 250);
    private final Color WARNA_KARTU = Color.WHITE;
    private final Color WARNA_TEKS = new Color(60, 40, 70);
    private final Color WARNA_GARIS = new Color(232, 210, 240);

    public KpopPlaylistGUI() {
        setTitle("K-Pop Playlist Manager");
        setSize(520, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());
        getContentPane().setBackground(WARNA_LATAR);

        add(buatHeader(), BorderLayout.NORTH);
        add(buatPanelUtama(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel buatHeader() {
        JPanel header = new JPanel(new GridLayout(2, 1, 0, 2));
        header.setBackground(WARNA_UTAMA);
        header.setBorder(new EmptyBorder(16, 22, 16, 22));

        JLabel judul = new JLabel("K-POP PLAYLIST MANAGER");
        judul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        judul.setForeground(Color.WHITE);

        JLabel subJudul = new JLabel("Kumpulkan lagu K-Pop favoritmu di sini");
        subJudul.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subJudul.setForeground(new Color(245, 225, 255));

        header.add(judul);
        header.add(subJudul);
        return header;
    }

    private JPanel buatPanelUtama() {
        JPanel utama = new JPanel(new BorderLayout(0, 12));
        utama.setBackground(WARNA_LATAR);
        utama.setBorder(new EmptyBorder(16, 20, 20, 20));

        utama.add(buatPanelForm(), BorderLayout.NORTH);
        utama.add(buatPanelDaftar(), BorderLayout.CENTER);
        return utama;
    }

    private JPanel buatPanelForm() {
        JPanel form = new JPanel(new BorderLayout(0, 12));
        form.setBackground(WARNA_LATAR);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(WARNA_KARTU);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(WARNA_GARIS),
                new EmptyBorder(14, 14, 14, 14)));

        JLabel judulLabel = new JLabel("Judul Lagu");
        judulLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        judulLabel.setForeground(WARNA_TEKS);

        judulField = new JTextField();
        judulField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        judulField.setForeground(WARNA_TEKS);
        judulField.setCaretColor(WARNA_TEKS);
        judulField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 190, 225)),
                new EmptyBorder(5, 9, 5, 9)));

        JLabel artisLabel = new JLabel("Artis");
        artisLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        artisLabel.setForeground(WARNA_TEKS);

        String[] daftarArtis = {
                "TWICE", "BLACKPINK", "NewJeans", "EXO", "BTS",
                "IVE", "LE SSERAFIM", "SEVENTEEN", "aespa",
                "Stray Kids", "ITZY", "Red Velvet"
        };
        artisCombo = new JComboBox<>(daftarArtis);
        artisCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        artisCombo.setForeground(WARNA_TEKS);
        artisCombo.setBackground(Color.WHITE);

        inputPanel.add(judulLabel);
        inputPanel.add(judulField);
        inputPanel.add(artisLabel);
        inputPanel.add(artisCombo);

        JPanel tombolPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        tombolPanel.setBackground(WARNA_LATAR);

        tambahButton = buatTombol("Tambah Lagu", WARNA_AKSEN);
        hapusButton = buatTombol("Hapus", WARNA_UTAMA);
        resetButton = buatTombol("Reset", new Color(155, 142, 166));

        tombolPanel.add(tambahButton);
        tombolPanel.add(hapusButton);
        tombolPanel.add(resetButton);

        form.add(inputPanel, BorderLayout.CENTER);
        form.add(tombolPanel, BorderLayout.SOUTH);
        return form;
    }

    private JPanel buatPanelDaftar() {
        JPanel panelDaftar = new JPanel(new BorderLayout(0, 8));
        panelDaftar.setBackground(WARNA_KARTU);
        panelDaftar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(WARNA_GARIS),
                new EmptyBorder(14, 14, 14, 14)));

        JLabel judulDaftar = new JLabel("Daftar Playlist");
        judulDaftar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        judulDaftar.setForeground(WARNA_UTAMA);

        playlistModel = new DefaultListModel<>();
        playlistModel.addElement("What Is Love? - TWICE");
        playlistModel.addElement("Love Shot - EXO");
        playlistModel.addElement("Ditto - NewJeans");

        playlistList = new JList<>(playlistModel);
        playlistList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        playlistList.setForeground(WARNA_TEKS);
        playlistList.setBackground(Color.WHITE);
        playlistList.setFixedCellHeight(34);
        playlistList.setSelectionBackground(new Color(243, 214, 245));
        playlistList.setSelectionForeground(WARNA_TEKS);

        playlistList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                label.setText((index + 1) + ". " + value);
                label.setBorder(new EmptyBorder(4, 8, 4, 8));
                return label;
            }
        });

        JScrollPane scrollPane = new JScrollPane(playlistList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(240, 230, 245)));

        totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        totalLabel.setForeground(WARNA_TEKS);
        perbaruiTotal();

        panelDaftar.add(judulDaftar, BorderLayout.NORTH);
        panelDaftar.add(scrollPane, BorderLayout.CENTER);
        panelDaftar.add(totalLabel, BorderLayout.SOUTH);
        return panelDaftar;
    }

    private JButton buatTombol(String teks, Color warna) {
        JButton tombol = new JButton(teks);
        tombol.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tombol.setBackground(warna);
        tombol.setForeground(Color.WHITE);
        tombol.setOpaque(true);
        tombol.setFocusPainted(false);
        tombol.setBorder(new EmptyBorder(9, 12, 9, 12));
        tombol.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tombol.addActionListener(this);
        return tombol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tambahButton) {
            tambahLagu();
        } else if (e.getSource() == hapusButton) {
            hapusLagu();
        } else if (e.getSource() == resetButton) {
            resetInput();
        }
    }

    private void tambahLagu() {
        String judul = judulField.getText().trim();

        if (judul.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Judul lagu harus diisi terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            judulField.requestFocusInWindow();
            return;
        }

        String artis = (String) artisCombo.getSelectedItem();
        playlistModel.addElement(judul + " - " + artis);
        perbaruiTotal();

        judulField.setText("");
        judulField.requestFocusInWindow();
    }

    private void hapusLagu() {
        int index = playlistList.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih lagu yang ingin dihapus terlebih dahulu.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        playlistModel.remove(index);
        perbaruiTotal();
        playlistList.clearSelection();
    }

    private void resetInput() {
        judulField.setText("");
        artisCombo.setSelectedIndex(0);
        playlistList.clearSelection();
        judulField.requestFocusInWindow();
    }

    private void perbaruiTotal() {
        totalLabel.setText("Total Lagu: " + playlistModel.size());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KpopPlaylistGUI());
    }
}

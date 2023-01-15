/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Categoria;
import Modelo.CategoriaDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Login;
import Modelo.LoginDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author usuariopc
 */
public class SistemaMainTecno extends javax.swing.JFrame {

    //Esto para que el sistema tome la fecha actual
    Date fecha = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fecha);

    Login login = new Login();
    LoginDAO loginDao = new LoginDAO();
    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedorDao = new ProveedorDAO();
    Cliente cliente = new Cliente();
    ClienteDAO clienteDao = new ClienteDAO();
    Categoria categoria = new Categoria();
    CategoriaDAO categoriaDao = new CategoriaDAO();
    Producto producto = new Producto();
    ProductoDAO productoDao = new ProductoDAO();
    DefaultTableModel modelo = new DefaultTableModel();

    public SistemaMainTecno() {
        initComponents();
        this.setLocationRelativeTo(null);
        //Autocompletado
        AutoCompleteDecorator.decorate(cbxProveedorProductos);
        AutoCompleteDecorator.decorate(cbxCategoriaProductos);
        txtIdUsuarios.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtNombreCategoria.setVisible(false);
        txtIdClientes.setVisible(false);
        productoDao.ConsultarProveedor(cbxProveedorProductos);
        productoDao.ConsultarCategoria(cbxCategoriaProductos);

    }

    public SistemaMainTecno(Login privilegios) {
        initComponents();
        this.setLocationRelativeTo(null);
        //Autocompletado
        AutoCompleteDecorator.decorate(cbxProveedorProductos);
        AutoCompleteDecorator.decorate(cbxCategoriaProductos);
        txtIdUsuarios.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtNombreCategoria.setVisible(false);
        txtIdClientes.setVisible(false);
        productoDao.ConsultarProveedor(cbxProveedorProductos);
        productoDao.ConsultarCategoria(cbxCategoriaProductos);
        if (privilegios.getRol().equals("Asistente")) {
            btnUsuarios.setEnabled(false);
            jTabbedPane2.setEnabledAt(1, false);
            LabelNombreVendedor.setText(privilegios.getNombre());
        } else {
            LabelNombreVendedor.setText(privilegios.getNombre());
        }
    }

    //Este metodo es apra limpiar todas la tablas
    public void LimpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Esta seccion para va a ser para listar las tablas
    public void ListarUsuarios() {
        List<Login> ListaUsuario = loginDao.ListaUsuario();
        modelo = (DefaultTableModel) TablaUsuarios.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListaUsuario.size(); i++) {
            ob[0] = ListaUsuario.get(i).getId();
            ob[1] = ListaUsuario.get(i).getNombre();
            ob[2] = ListaUsuario.get(i).getCorreo();
            ob[3] = ListaUsuario.get(i).getPass();
            ob[4] = ListaUsuario.get(i).getRol();
            modelo.addRow(ob);
        }
        TablaUsuarios.setModel(modelo);
    }

    public void ListarProveedores() {
        List<Proveedor> ListaProveedor = proveedorDao.ListarProveedor();
        modelo = (DefaultTableModel) TablaProveedor.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListaProveedor.size(); i++) {
            ob[0] = ListaProveedor.get(i).getId();
            ob[1] = ListaProveedor.get(i).getCuit();
            ob[2] = ListaProveedor.get(i).getEmpresa();
            ob[3] = ListaProveedor.get(i).getNombre();
            ob[4] = ListaProveedor.get(i).getTelefono();
            ob[5] = ListaProveedor.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TablaProveedor.setModel(modelo);
    }

    public void ListarClientes() {
        List<Cliente> ListaCliente = clienteDao.ListarCliente();
        modelo = (DefaultTableModel) TablaClientes.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListaCliente.size(); i++) {
            ob[0] = ListaCliente.get(i).getId();
            ob[1] = ListaCliente.get(i).getDni();
            ob[2] = ListaCliente.get(i).getNombre();
            ob[3] = ListaCliente.get(i).getTelefono();
            ob[4] = ListaCliente.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TablaClientes.setModel(modelo);
    }

    public void ListarCategorias() {
        List<Categoria> ListaCategorias = categoriaDao.ListarCategorias();
        modelo = (DefaultTableModel) TablaCategoria.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < ListaCategorias.size(); i++) {
            ob[0] = ListaCategorias.get(i).getId();
            ob[1] = ListaCategorias.get(i).getNombre();
            modelo.addRow(ob);
        }
        TablaCategoria.setModel(modelo);
    }

    public void ListarProductos() {

        List<Producto> ListaProducto = productoDao.ListarProductos();
        modelo = (DefaultTableModel) TablaProductos.getModel();
        Object[] ob = new Object[9];
        for (int i = 0; i < ListaProducto.size(); i++) {
            ob[0] = ListaProducto.get(i).getId();
            ob[1] = ListaProducto.get(i).getCodigo();
            ob[2] = ListaProducto.get(i).getDescripcion();
            ob[3] = ListaProducto.get(i).getCantidad();
            ob[4] = ListaProducto.get(i).getProveedor();
            ob[5] = ListaProducto.get(i).getCategoria();
            ob[6] = ListaProducto.get(i).getPrecioCosto();
            ob[7] = ListaProducto.get(i).getPorcentaje();
            ob[8] = ListaProducto.get(i).getPrecioFinal();
            modelo.addRow(ob);
        }
        TablaProductos.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        LabelNombreVendedor = new javax.swing.JLabel();
        btnUsuarios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnEliminarNVenta = new javax.swing.JButton();
        txtCodigoNVenta = new javax.swing.JTextField();
        txtDesNVenta = new javax.swing.JTextField();
        txtCantidadNVenta = new javax.swing.JTextField();
        txtPrecioNVenta = new javax.swing.JTextField();
        txtStockNVenta = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        TablaNVenta = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtDniClienteNVenta = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        btnBuscarNVenta = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        txtIdiClienteNVenta = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtDirecNClienteNVenta = new javax.swing.JTextField();
        txtTelefonoNClienteNVenta = new javax.swing.JTextField();
        txtNombreNClienteNVenta = new javax.swing.JTextField();
        txtDniNClienteNVenta = new javax.swing.JTextField();
        btnGuardarNVenta = new javax.swing.JButton();
        btnCerrarNVenta = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        txtTotalPagarNVenta = new javax.swing.JLabel();
        btnTicketNVenta = new javax.swing.JButton();
        txtIdProductoNVenta = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        btnEliminarUsuarios = new javax.swing.JButton();
        btnAgregarUsuarios = new javax.swing.JButton();
        txtIdUsuarios = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCuitProveedor = new javax.swing.JTextField();
        txtEmpresaProveedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDireccionProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProveedor = new javax.swing.JTable();
        btnGuardarProveedor = new javax.swing.JButton();
        btnActualizarProveedor = new javax.swing.JButton();
        btnNuevoProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtDniClientes = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDireccionClientes = new javax.swing.JTextField();
        txtNombreClientes = new javax.swing.JTextField();
        txtTelefonoClientes = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        btnGuardarClientes = new javax.swing.JButton();
        btnActualizarClientes = new javax.swing.JButton();
        btnEliminarClientes = new javax.swing.JButton();
        btnNuevoClientes = new javax.swing.JButton();
        txtIdClientes = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDescripcionProductos = new javax.swing.JTextField();
        txtCodigoProductos = new javax.swing.JTextField();
        txtCantidadProductos = new javax.swing.JTextField();
        cbxProveedorProductos = new javax.swing.JComboBox<>();
        cbxCategoriaProductos = new javax.swing.JComboBox<>();
        txtPrecioCostoProductos = new javax.swing.JTextField();
        cbxPorcentajeProductos = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        btnGuardarProductos = new javax.swing.JButton();
        btnActualizarProductos = new javax.swing.JButton();
        btnEliminarProductos = new javax.swing.JButton();
        btnExcelProductos = new javax.swing.JButton();
        btnNuevoProductos = new javax.swing.JButton();
        btnPdfProductos = new javax.swing.JButton();
        txtIdProductos = new javax.swing.JTextField();
        txtBusquedaProducto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtPrecioFinalProductos = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaCategoria = new javax.swing.JTable();
        btnEliminarCategoria = new javax.swing.JButton();
        btnGuardarCategoria = new javax.swing.JButton();
        txtNombreCategoria = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        btnPdfVenta = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNombreConfig = new javax.swing.JTextField();
        txtTipoConfig = new javax.swing.JTextField();
        txtDireccionConfig = new javax.swing.JTextField();
        txtTelefonoConfig = new javax.swing.JTextField();
        txtCuitConfig = new javax.swing.JTextField();
        btnActualizarConfig = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 0, 204));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/encabezadoTecno2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 120));

        jPanel4.setBackground(new java.awt.Color(0, 0, 255));

        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        LabelNombreVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelNombreVendedor.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombreVendedor.setText("Nombre del Vendedor");

        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        btnCategorias.setText("Categorias");
        btnCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriasActionPerformed(evt);
            }
        });

        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfig.setText("Config");
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(LabelNombreVendedor)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(LabelNombreVendedor)
                .addGap(18, 18, 18)
                .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 230, 520));

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("CODIGO");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("DESCRIPCION");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("CANTIDAD");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("PRECIO");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 255));
        jLabel32.setText("STOCK DISPONIBLE");

        btnEliminarNVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N

        txtDesNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDesNVentaActionPerformed(evt);
            }
        });

        txtCantidadNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadNVentaActionPerformed(evt);
            }
        });

        txtPrecioNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioNVentaActionPerformed(evt);
            }
        });

        txtStockNVenta.setEditable(false);
        txtStockNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockNVentaActionPerformed(evt);
            }
        });

        TablaNVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO U.", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(TablaNVenta);
        if (TablaNVenta.getColumnModel().getColumnCount() > 0) {
            TablaNVenta.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaNVenta.getColumnModel().getColumn(1).setPreferredWidth(50);
            TablaNVenta.getColumnModel().getColumn(2).setPreferredWidth(20);
            TablaNVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaNVenta.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        jLabel35.setText("DNI:");

        jLabel36.setText("NOMBRE:");

        jTextField26.setEditable(false);
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        btnBuscarNVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        btnBuscarNVenta.setText("BUSCAR");

        jLabel37.setText("TELEFONO:");

        jLabel38.setText("DIRECCION:");

        jTextField27.setEditable(false);

        jTextField28.setEditable(false);

        txtIdiClienteNVenta.setEditable(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(btnBuscarNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDniClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(44, 44, 44)
                                .addComponent(txtIdiClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtDniClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdiClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnBuscarNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel36)
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente Frecuente", jPanel12);

        jLabel39.setText("DNI:");

        jLabel40.setText("NOMBRE:");

        jLabel41.setText("TELEFONO:");

        jLabel42.setText("DIRECCION:");

        txtNombreNClienteNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreNClienteNVentaActionPerformed(evt);
            }
        });

        btnGuardarNVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarNVenta.setText("GUARDAR");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDniNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel40)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNombreNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDirecNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarNVenta)
                .addGap(26, 26, 26))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtDniNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtNombreNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtTelefonoNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtDirecNClienteNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nuevo Cliente", jPanel13);

        btnCerrarNVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnCerrarNVenta.setText("CERRAR VENTA");

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel33.setText("Total a pagar:");

        txtTotalPagarNVenta.setText("--------------------");

        btnTicketNVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnTicketNVenta.setText("TICKET");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel29)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel30))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtCodigoNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDesNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCantidadNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(txtPrecioNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txtStockNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtIdProductoNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(256, 256, 256)))
                        .addComponent(btnEliminarNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(txtTotalPagarNVenta)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCerrarNVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTicketNVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdProductoNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDesNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnEliminarNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalPagarNVenta)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addComponent(btnCerrarNVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTicketNVenta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Nueva Venta", jPanel2);

        TablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CORREO", "PASSWORD", "ROL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaUsuarios);
        if (TablaUsuarios.getColumnModel().getColumnCount() > 0) {
            TablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
            TablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(80);
            TablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btnEliminarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarUsuarios.setText("Eliminar");
        btnEliminarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuariosActionPerformed(evt);
            }
        });

        btnAgregarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnAgregarUsuarios.setText("Agregar");
        btnAgregarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuariosActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ayuda.png"))); // NOI18N
        jButton1.setText("Refrescar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtIdUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnAgregarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnEliminarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Usuarios", jPanel6);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CUIT:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("EMPRESA:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("NOMBRE:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("TELEFONO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("DIRECCION:");

        TablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CUIT", "EMPRESA", "NOMBRE", "TELEFONO", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProveedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaProveedor);
        if (TablaProveedor.getColumnModel().getColumnCount() > 0) {
            TablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaProveedor.getColumnModel().getColumn(1).setPreferredWidth(30);
            TablaProveedor.getColumnModel().getColumn(2).setPreferredWidth(50);
            TablaProveedor.getColumnModel().getColumn(3).setPreferredWidth(20);
            TablaProveedor.getColumnModel().getColumn(4).setPreferredWidth(30);
            TablaProveedor.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        btnGuardarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarProveedor.setText("GUARDAR");
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnActualizarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarProveedor.setText("ACTUALIZAR");
        btnActualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProveedorActionPerformed(evt);
            }
        });

        btnNuevoProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProveedor.setText("NUEVO");
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProveedor.setText("ELIMINAR");
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCuitProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpresaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCuitProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmpresaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane2.addTab("Proveedores", jPanel7);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("DNI:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("NOMBRE:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("DIRECCION:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("TELEFONO:");

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "NOMBRE", "TELEFONO", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaClientesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaClientes);
        if (TablaClientes.getColumnModel().getColumnCount() > 0) {
            TablaClientes.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaClientes.getColumnModel().getColumn(1).setPreferredWidth(30);
            TablaClientes.getColumnModel().getColumn(2).setPreferredWidth(50);
            TablaClientes.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        btnGuardarClientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarClientes.setText("GUARDAR");
        btnGuardarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClientesActionPerformed(evt);
            }
        });

        btnActualizarClientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarClientes.setText("ACTUALIZAR");
        btnActualizarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClientesActionPerformed(evt);
            }
        });

        btnEliminarClientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarClientes.setText("ELIMINAR");
        btnEliminarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClientesActionPerformed(evt);
            }
        });

        btnNuevoClientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNuevoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoClientes.setText("NUEVO");
        btnNuevoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtIdClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDniClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccionClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnGuardarClientes))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizarClientes)
                            .addComponent(btnEliminarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(btnNuevoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtDniClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtIdClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNombreClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTelefonoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDireccionClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(btnGuardarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)))
        );

        jTabbedPane2.addTab("Clientes", jPanel8);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("CODIGO:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DESCRIPCION:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("CANTIDAD:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("PRECIO COSTO:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("PROVEEDOR");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("PORCENTAJE:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("CATEGORIA:");

        cbxProveedorProductos.setEditable(true);

        cbxCategoriaProductos.setEditable(true);

        cbxPorcentajeProductos.setEditable(true);
        cbxPorcentajeProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100", "105", "110", "115", "120" }));

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "DESCRIPCION", "CANTIDAD", "PROVEEDOR", "CATEGORIA", "COSTO", "%", "VENTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaProductos);
        if (TablaProductos.getColumnModel().getColumnCount() > 0) {
            TablaProductos.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaProductos.getColumnModel().getColumn(1).setPreferredWidth(50);
            TablaProductos.getColumnModel().getColumn(2).setPreferredWidth(10);
            TablaProductos.getColumnModel().getColumn(3).setPreferredWidth(10);
            TablaProductos.getColumnModel().getColumn(4).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(5).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(6).setPreferredWidth(20);
            TablaProductos.getColumnModel().getColumn(7).setPreferredWidth(20);
            TablaProductos.getColumnModel().getColumn(8).setPreferredWidth(20);
        }

        btnGuardarProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarProductos.setText("GUARDAR");
        btnGuardarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductosActionPerformed(evt);
            }
        });

        btnActualizarProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarProductos.setText("ACTUALIZAR");
        btnActualizarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProductosActionPerformed(evt);
            }
        });

        btnEliminarProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProductos.setText("ELIMINAR");
        btnEliminarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductosActionPerformed(evt);
            }
        });

        btnExcelProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcelProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcelProductos.setText("REPORTE TOTAL");

        btnNuevoProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProductos.setText("NUEVO");
        btnNuevoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductosActionPerformed(evt);
            }
        });

        btnPdfProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPdfProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPdfProductos.setText("REPORTE DE FALTANTES");

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jButton2.setText("Buscar Producto");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(cbxCategoriaProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPorcentajeProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescripcionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbxProveedorProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCantidadProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioCostoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnGuardarProductos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizarProductos))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnEliminarProductos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNuevoProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnExcelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(txtIdProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtPrecioFinalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtBusquedaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(btnPdfProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDescripcionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtCantidadProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cbxProveedorProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cbxCategoriaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPrecioCostoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cbxPorcentajeProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusquedaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnPdfProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioFinalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane2.addTab("Productos", jPanel9);

        TablaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCategoriaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaCategoria);
        if (TablaCategoria.getColumnModel().getColumnCount() > 0) {
            TablaCategoria.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaCategoria.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        btnEliminarCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarCategoria.setText("ELIMINAR");
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        btnGuardarCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarCategoria.setText("GUARDAR");
        btnGuardarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCategoriaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("NUEVA CATEGORIA:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnGuardarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnGuardarCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Categoria", jPanel10);

        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENDEDOR", "COMPRADOR", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(TablaVentas);
        if (TablaVentas.getColumnModel().getColumnCount() > 0) {
            TablaVentas.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaVentas.getColumnModel().getColumn(1).setPreferredWidth(30);
            TablaVentas.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaVentas.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        btnPdfVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPdfVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPdfVenta.setText("VER VENTA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(btnPdfVenta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(btnPdfVenta)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jTabbedPane2.addTab("Ventas", jPanel5);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setText("DATOS DE LA EMPRESA");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("CUIT:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("NOMBRE:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("TELEFONO:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("DIRECCION:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("TIPO DE EMPRESA:");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logoTecno.png"))); // NOI18N

        btnActualizarConfig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizarConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarConfig.setText("ACTUALIZAR");

        jLabel27.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel27.setText("Todos los Derechos Reservados @CopyRigth - TecnoMigui- ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel20))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTipoConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel24))
                                        .addGap(62, 62, 62)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDireccionConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefonoConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCuitConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(84, 84, 84)
                                .addComponent(jLabel26))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(btnActualizarConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel27)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCuitConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtNombreConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtTelefonoConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtDireccionConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtTipoConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnActualizarConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel27)
                .addGap(26, 26, 26))
        );

        jTabbedPane2.addTab("Config", jPanel11);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 700, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDesNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDesNVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesNVentaActionPerformed

    private void txtCantidadNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadNVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadNVentaActionPerformed

    private void txtPrecioNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioNVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioNVentaActionPerformed

    private void txtStockNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockNVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockNVentaActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void txtNombreNClienteNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreNClienteNVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreNClienteNVentaActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnAgregarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuariosActionPerformed
        // TODO add your handling code here:
        RegistrarMain reg = new RegistrarMain();
        reg.setVisible(true);

    }//GEN-LAST:event_btnAgregarUsuariosActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarUsuarios();
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarProveedores();
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarClientes();

        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarProductos();
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarCategorias();

        jTabbedPane2.setSelectedIndex(5);
    }//GEN-LAST:event_btnCategoriasActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(6);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(7);
    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnEliminarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuariosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdUsuarios.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdUsuarios.getText());
                loginDao.EliminarUsuario(id);
                LimpiarTabla();
                ListarUsuarios();
                txtIdUsuarios.setText("");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        }
    }//GEN-LAST:event_btnEliminarUsuariosActionPerformed

    private void TablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaUsuariosMouseClicked
        // TODO add your handling code here:
        int fila = TablaUsuarios.rowAtPoint(evt.getPoint());
        txtIdUsuarios.setText(TablaUsuarios.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TablaUsuariosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LimpiarTabla();
        ListarUsuarios();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Debe de Usar los Botones");
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtCuitProveedor.getText()) && !"".equals(txtEmpresaProveedor.getText()) && !"".equals(txtNombreProveedor.getText()) && !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtDireccionProveedor.getText())) {
            proveedor.setCuit(Long.parseLong(txtCuitProveedor.getText()));
            proveedor.setEmpresa(txtEmpresaProveedor.getText());
            proveedor.setNombre(txtNombreProveedor.getText());
            proveedor.setTelefono(Long.parseLong(txtTelefonoProveedor.getText()));
            proveedor.setDireccion(txtDireccionProveedor.getText());
            proveedor.setFecha(fechaActual);
            proveedorDao.GuardarProveedores(proveedor);
            JOptionPane.showMessageDialog(null, "Proveedor guardado correctamente");
            LimpiarTabla();
            ListarProveedores();
            LimpiarCamposProveedor();

        } else {
            JOptionPane.showMessageDialog(null, "Los campos son obligatorios");
        }

    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        // TODO add your handling code here:
        LimpiarCamposProveedor();
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void TablaProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProveedorMouseClicked
        // TODO add your handling code here:
        int fila = TablaProveedor.rowAtPoint(evt.getPoint());
        txtIdProveedor.setText(TablaProveedor.getValueAt(fila, 0).toString());
        txtCuitProveedor.setText(TablaProveedor.getValueAt(fila, 1).toString());
        txtEmpresaProveedor.setText(TablaProveedor.getValueAt(fila, 2).toString());
        txtNombreProveedor.setText(TablaProveedor.getValueAt(fila, 3).toString());
        txtTelefonoProveedor.setText(TablaProveedor.getValueAt(fila, 4).toString());
        txtDireccionProveedor.setText(TablaProveedor.getValueAt(fila, 5).toString());
        txtCuitProveedor.setEnabled(false);
        txtEmpresaProveedor.setEnabled(false);

    }//GEN-LAST:event_TablaProveedorMouseClicked

    private void btnActualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProveedorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdProveedor.getText())) {
            if (!"".equals(txtNombreProveedor.getText()) && !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtDireccionProveedor.getText())) {
                proveedor.setCuit(Long.parseLong(txtCuitProveedor.getText()));
                proveedor.setEmpresa(txtEmpresaProveedor.getText());
                proveedor.setNombre(txtNombreProveedor.getText());
                proveedor.setTelefono(Long.parseLong(txtTelefonoProveedor.getText()));
                proveedor.setDireccion(txtDireccionProveedor.getText());
                proveedor.setId(Integer.parseInt(txtIdProveedor.getText()));
                proveedorDao.ActualizarProveedor(proveedor);
                JOptionPane.showMessageDialog(null, "Proveedor Actualizado");
                LimpiarTabla();
                ListarProveedores();
                LimpiarCamposProveedor();
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnActualizarProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                proveedorDao.EliminarProveedor(id);
                LimpiarTabla();
                ListarProveedores();
                LimpiarCamposProveedor();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelado");
                LimpiarCamposProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnGuardarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClientesActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtDniClientes.getText()) && !"".equals(txtNombreClientes.getText()) && !"".equals(txtTelefonoClientes.getText()) && !"".equals(txtDireccionClientes.getText())) {
            Cliente clienteBuscado = new Cliente();
            clienteBuscado = clienteDao.BuscarCliente(Long.parseLong(txtDniClientes.getText()));
            if (clienteBuscado == null) {
                cliente.setDni(Long.parseLong(txtDniClientes.getText()));
                cliente.setNombre(txtNombreClientes.getText());
                cliente.setTelefono(Long.parseLong(txtTelefonoClientes.getText()));
                cliente.setDireccion(txtDireccionClientes.getText());
                cliente.setFecha(fechaActual);
                clienteDao.GuardarClientes(cliente);
                JOptionPane.showMessageDialog(null, "Cliente guardado correctamente");
                LimpiarTabla();
                ListarClientes();
                LimpiarCamposCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente ya existe, no se puede guardar");
                LimpiarCamposCliente();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Los campos son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarClientesActionPerformed

    private void TablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaClientesMouseClicked
        // TODO add your handling code here:
        int fila = TablaClientes.rowAtPoint(evt.getPoint());
        txtIdClientes.setText(TablaClientes.getValueAt(fila, 0).toString());
        txtDniClientes.setText(TablaClientes.getValueAt(fila, 1).toString());
        txtNombreClientes.setText(TablaClientes.getValueAt(fila, 2).toString());
        txtTelefonoClientes.setText(TablaClientes.getValueAt(fila, 3).toString());
        txtDireccionClientes.setText(TablaClientes.getValueAt(fila, 4).toString());
        txtDniClientes.setEnabled(false);
        txtNombreClientes.setEnabled(false);
    }//GEN-LAST:event_TablaClientesMouseClicked

    private void btnNuevoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClientesActionPerformed
        // TODO add your handling code here:
        LimpiarCamposCliente();
    }//GEN-LAST:event_btnNuevoClientesActionPerformed

    private void btnActualizarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClientesActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdClientes.getText())) {
            if (!"".equals(txtTelefonoClientes.getText()) && !"".equals(txtDireccionClientes.getText())) {
//                proveedor.setCuit(Long.parseLong(txtCuitProveedor.getText()));
//                proveedor.setEmpresa(txtEmpresaProveedor.getText());
//                proveedor.setNombre(txtNombreProveedor.getText());
                cliente.setTelefono(Long.parseLong(txtTelefonoClientes.getText()));
                cliente.setDireccion(txtDireccionClientes.getText());
                cliente.setId(Integer.parseInt(txtIdClientes.getText()));
                clienteDao.ActualizarCliente(cliente);
                JOptionPane.showMessageDialog(null, "Cliente Actualizado");
                LimpiarTabla();
                ListarClientes();
                LimpiarCamposCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnActualizarClientesActionPerformed

    private void btnEliminarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClientesActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdClientes.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdClientes.getText());
                clienteDao.EliminarCliente(id);
                LimpiarTabla();
                ListarClientes();
                LimpiarCamposCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelado");
                LimpiarCamposCliente();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnEliminarClientesActionPerformed

    private void TablaCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCategoriaMouseClicked
        // TODO add your handling code here:
        int fila = TablaCategoria.rowAtPoint(evt.getPoint());
        txtIdCategoria.setText(TablaCategoria.getValueAt(fila, 0).toString());
        txtNombreCategoria.setText(TablaCategoria.getValueAt(fila, 1).toString());
        txtNombreCategoria.setEnabled(false);
    }//GEN-LAST:event_TablaCategoriaMouseClicked

    private void btnGuardarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCategoriaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtNombreCategoria.getText())) {
            categoria.setNombre(txtNombreCategoria.getText());
            categoriaDao.GuardarCategoria(categoria);
            JOptionPane.showMessageDialog(null, "Categoria guardado correctamente");
            LimpiarTabla();
            ListarCategorias();
            LimpiarCamposCategoria();

        } else {
            JOptionPane.showMessageDialog(null, "Los campos son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdCategoria.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar? Esto provocara que algunos productos se borre esta categoria");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdCategoria.getText());
                categoriaDao.EliminarCategoria(id);
                LimpiarTabla();
                ListarCategorias();
                LimpiarCamposCategoria();
            } else {
                JOptionPane.showMessageDialog(null, "Cancelado");
                LimpiarCamposCategoria();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        }
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void btnGuardarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtCodigoProductos.getText()) && !"".equals(txtDescripcionProductos.getText()) && !"".equals(txtCantidadProductos.getText()) && !"".equals(txtPrecioCostoProductos.getText())) {
            
            boolean productoBuscado = productoDao.BuscarProductoBooleano(Long.parseLong(txtCodigoProductos.getText()));
            if (!productoBuscado) {
                producto.setCodigo(Long.parseLong(txtCodigoProductos.getText()));
                producto.setDescripcion(txtDescripcionProductos.getText());
                producto.setCantidad(Integer.parseInt(txtCantidadProductos.getText()));
                producto.setProveedor(cbxProveedorProductos.getSelectedItem().toString());
                producto.setCategoria(cbxCategoriaProductos.getSelectedItem().toString());
                double precioCosto = Double.parseDouble(txtPrecioCostoProductos.getText());
                int porcentaje = Integer.parseInt(cbxPorcentajeProductos.getSelectedItem().toString());
                producto.setPrecioCosto(precioCosto);
                producto.setPorcentaje(porcentaje);
                double precioFinal = precioCosto + ((porcentaje * precioCosto) / 100);
                producto.setPrecioFinal(precioFinal);
                producto.setFecha(fechaActual);
                productoDao.GuardarProductos(producto);
                JOptionPane.showMessageDialog(null, "Producto guardado correctamente");
                LimpiarTabla();
                ListarProductos();
                LimpiarCamposProductos();
            } else {
                JOptionPane.showMessageDialog(null, "El producto Ya existe no se puede agregar");
                LimpiarCamposProductos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarProductosActionPerformed

    private void TablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductosMouseClicked
        // TODO add your handling code here:
        int fila = TablaProductos.rowAtPoint(evt.getPoint());
        txtIdProductos.setText(TablaProductos.getValueAt(fila, 0).toString());
        txtCodigoProductos.setText(TablaProductos.getValueAt(fila, 1).toString());
        txtDescripcionProductos.setText(TablaProductos.getValueAt(fila, 2).toString());
        txtCantidadProductos.setText(TablaProductos.getValueAt(fila, 3).toString());
        cbxProveedorProductos.setSelectedItem(TablaProductos.getValueAt(fila, 4).toString());
        cbxCategoriaProductos.setSelectedItem(TablaProductos.getValueAt(fila, 5).toString());
        txtPrecioCostoProductos.setText(TablaProductos.getValueAt(fila, 6).toString());
        cbxPorcentajeProductos.setSelectedItem(TablaProductos.getValueAt(fila, 7).toString());
        txtPrecioFinalProductos.setText(TablaProductos.getValueAt(fila, 8).toString());

        txtCodigoProductos.setEnabled(false);
        txtDescripcionProductos.setEnabled(false);
        cbxCategoriaProductos.setEnabled(false);
    }//GEN-LAST:event_TablaProductosMouseClicked

    private void btnNuevoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductosActionPerformed
        // TODO add your handling code here:
        LimpiarCamposProductos();
    }//GEN-LAST:event_btnNuevoProductosActionPerformed

    private void btnActualizarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProductosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdProductos.getText())) {
            if (!"".equals(txtCantidadProductos.getText()) && !"".equals(txtPrecioCostoProductos.getText())) {
                producto.setCantidad(Integer.parseInt(txtCantidadProductos.getText()));
                producto.setProveedor(cbxProveedorProductos.getSelectedItem().toString());
                Double PrecioCosto = Double.parseDouble(txtPrecioCostoProductos.getText());
                producto.setPrecioCosto(PrecioCosto);
                int Porcentaje = Integer.parseInt(cbxPorcentajeProductos.getSelectedItem().toString());
                producto.setPorcentaje(Porcentaje);
                double precioFinal = PrecioCosto + ((Porcentaje * PrecioCosto) / 100);
                producto.setPrecioFinal(precioFinal);
                producto.setId(Integer.parseInt(txtIdProductos.getText()));
                productoDao.ActualizarProducto(producto);
                JOptionPane.showMessageDialog(null, "Produto Actualizado correctamente");
                LimpiarTabla();
                ListarProductos();
                LimpiarCamposProductos();

            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Elija una Fila o Busque el Producto");
        }

    }//GEN-LAST:event_btnActualizarProductosActionPerformed

    private void btnEliminarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdProductos.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProductos.getText());
                productoDao.EliminarProducto(id);
                JOptionPane.showMessageDialog(null, "Producto Eliminado con Exito");
                LimpiarTabla();
                ListarProductos();
                LimpiarCamposProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion Cancelada");
                LimpiarCamposProductos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Elija una Fila o Busque el Producto");
        }
    }//GEN-LAST:event_btnEliminarProductosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SistemaMainTecno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaMainTecno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaMainTecno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaMainTecno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaMainTecno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelNombreVendedor;
    private javax.swing.JTable TablaCategoria;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTable TablaNVenta;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTable TablaProveedor;
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JTable TablaVentas;
    private javax.swing.JButton btnActualizarClientes;
    private javax.swing.JButton btnActualizarConfig;
    private javax.swing.JButton btnActualizarProductos;
    private javax.swing.JButton btnActualizarProveedor;
    private javax.swing.JButton btnAgregarUsuarios;
    private javax.swing.JButton btnBuscarNVenta;
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnCerrarNVenta;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnEliminarClientes;
    private javax.swing.JButton btnEliminarNVenta;
    private javax.swing.JButton btnEliminarProductos;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarUsuarios;
    private javax.swing.JButton btnExcelProductos;
    private javax.swing.JButton btnGuardarCategoria;
    private javax.swing.JButton btnGuardarClientes;
    private javax.swing.JButton btnGuardarNVenta;
    private javax.swing.JButton btnGuardarProductos;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevoClientes;
    private javax.swing.JButton btnNuevoProductos;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnPdfProductos;
    private javax.swing.JButton btnPdfVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnTicketNVenta;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVentas;
    private javax.swing.JComboBox<String> cbxCategoriaProductos;
    private javax.swing.JComboBox<String> cbxPorcentajeProductos;
    private javax.swing.JComboBox<String> cbxProveedorProductos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField txtBusquedaProducto;
    private javax.swing.JTextField txtCantidadNVenta;
    private javax.swing.JTextField txtCantidadProductos;
    private javax.swing.JTextField txtCodigoNVenta;
    private javax.swing.JTextField txtCodigoProductos;
    private javax.swing.JTextField txtCuitConfig;
    private javax.swing.JTextField txtCuitProveedor;
    private javax.swing.JTextField txtDesNVenta;
    private javax.swing.JTextField txtDescripcionProductos;
    private javax.swing.JTextField txtDirecNClienteNVenta;
    private javax.swing.JTextField txtDireccionClientes;
    private javax.swing.JTextField txtDireccionConfig;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDniClienteNVenta;
    private javax.swing.JTextField txtDniClientes;
    private javax.swing.JTextField txtDniNClienteNVenta;
    private javax.swing.JTextField txtEmpresaProveedor;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdClientes;
    private javax.swing.JTextField txtIdProductoNVenta;
    private javax.swing.JTextField txtIdProductos;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdUsuarios;
    private javax.swing.JTextField txtIdiClienteNVenta;
    private javax.swing.JTextField txtNombreCategoria;
    private javax.swing.JTextField txtNombreClientes;
    private javax.swing.JTextField txtNombreConfig;
    private javax.swing.JTextField txtNombreNClienteNVenta;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPrecioCostoProductos;
    private javax.swing.JTextField txtPrecioFinalProductos;
    private javax.swing.JTextField txtPrecioNVenta;
    private javax.swing.JTextField txtStockNVenta;
    private javax.swing.JTextField txtTelefonoClientes;
    private javax.swing.JTextField txtTelefonoConfig;
    private javax.swing.JTextField txtTelefonoNClienteNVenta;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTipoConfig;
    private javax.swing.JLabel txtTotalPagarNVenta;
    // End of variables declaration//GEN-END:variables

    private void LimpiarCamposProveedor() {
        txtIdProveedor.setText("");
        txtCuitProveedor.setText("");
        txtEmpresaProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtCuitProveedor.setEnabled(true);
        txtEmpresaProveedor.setEnabled(true);
    }

    private void LimpiarCamposCliente() {
        txtIdClientes.setText("");
        txtDniClientes.setText("");
        txtNombreClientes.setText("");
        txtTelefonoClientes.setText("");
        txtDireccionClientes.setText("");
        txtDniClientes.setEnabled(true);
        txtNombreClientes.setEnabled(true);
    }

    private void LimpiarCamposCategoria() {
        txtIdCategoria.setText("");
        txtNombreCategoria.setText("");
        txtNombreCategoria.setEnabled(true);
    }

    private void LimpiarCamposProductos() {
        txtIdProductos.setText("");
        txtCodigoProductos.setText("");
        txtDescripcionProductos.setText("");
        txtCantidadProductos.setText("");
        txtPrecioCostoProductos.setText("");
        txtPrecioFinalProductos.setText("");
        txtBusquedaProducto.setText("");
        txtCodigoProductos.setEnabled(true);
        txtDescripcionProductos.setEnabled(true);
        cbxCategoriaProductos.setEnabled(true);
    }

}

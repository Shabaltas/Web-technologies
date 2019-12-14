package by.training.task4.controller;

import by.training.task4.model.Banks;
import by.training.task4.service.FileHandler;
import by.training.task4.service.ParserService;
import org.xml.sax.SAXException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@MultipartConfig
public class ParsingServlet extends HttpServlet {
    private static final String DEFAULT_TEMP_FILE = "temp";
    private static final String RESULT_JSP = "/jsp/result.jsp";
    private static final String BANKS_ATTRIBUTE = "banks";
    private static final String PARSER_TYPE_ATTRIBUTE = "parserType";
    private static final Logger LOGGER =  LogManager.getLogger(ParsingServlet.class.getSimpleName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String parserType = req.getParameter(PARSER_TYPE_ATTRIBUTE);
        try {
            new FileHandler().updateFile(DEFAULT_TEMP_FILE, req);
            Banks banks = new ParserService().parseXML(parserType, DEFAULT_TEMP_FILE);
            req.setAttribute(BANKS_ATTRIBUTE, banks.getDeposits());
            req.setAttribute(PARSER_TYPE_ATTRIBUTE, parserType);
            req.getRequestDispatcher(RESULT_JSP).forward(req, resp);
        } catch (IOException | SAXException e) {
            LOGGER.error(e);
        }
    }
}

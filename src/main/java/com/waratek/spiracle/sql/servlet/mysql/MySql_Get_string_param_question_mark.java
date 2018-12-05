/*
 *  Copyright 2017 Waratek Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.waratek.spiracle.sql.servlet.mysql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waratek.spiracle.sql.servlet.util.ParameterNullFix;
import com.waratek.spiracle.sql.util.SelectUtil;

/**
 * Servlet implementation class Get_string
 */

public class MySql_Get_string_param_question_mark extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySql_Get_string_param_question_mark() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeRequest(request, response);
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext application = this.getServletConfig().getServletContext();
        List queryStringList = new ArrayList ();
        queryStringList.add("name");

        Map nullSanitizedMap = ParameterNullFix.sanitizeNull(queryStringList, request);

        String name = (String)nullSanitizedMap.get("name");

        // The '?' syntax is invalid for MySQL, so this query never successfully execute.
        String sql = "SELECT top 5 id, name, surname FROM users where name <> ? and name = '" + name + "'";

        Boolean showErrors = Boolean.TRUE;
        Boolean allResults = Boolean.TRUE;
        Boolean showOutput = Boolean.TRUE;

        SelectUtil.executeQuerySetString(sql, application, request, response, showErrors, allResults, showOutput);
    }

}